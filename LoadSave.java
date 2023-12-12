import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.stream.JsonReader;
public class LoadSave {

    public LoadSave(String loadFileName){

        /* 
        try {
            BufferedReader data = new BufferedReader(new File("/saves/save.json"));
        } catch (Exception e) {
            
        }
        */
    }
    public Map load(){
        JsonReader jsonReader;
        try {
            jsonReader = new JsonReader(new FileReader("save.json"));

            GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
            gsonBuilder.registerTypeAdapter(Space.class, new InterfaceAdapter<Space>());
            Gson gson = gsonBuilder.create();
            return gson.fromJson(jsonReader, Map.class);
        } catch (FileNotFoundException e) {
        }
        return null;
    }
    public void save(Space map){
        try {
            Writer writer = new FileWriter("save.json");
            GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
            gsonBuilder.registerTypeAdapter(Space.class, new InterfaceAdapter<Space>());
            Gson gson = gsonBuilder.create();
            gson.toJson(map, writer);
            writer.flush();
            writer.close();
        } catch (Exception e) {
        }
    }
}
final class InterfaceAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T> {
    public JsonElement serialize(T object, Type interfaceType, JsonSerializationContext context) {
        final JsonObject wrapper = new JsonObject();
        
        wrapper.addProperty("type", object.getClass().getName());
        wrapper.add("data", context.serialize(object));
        
        return wrapper;
    }
    public T deserialize(JsonElement elem, Type interfaceType, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject wrapper = (JsonObject) elem;
        final JsonElement typeName = get(wrapper, "type");
        final JsonElement data = get(wrapper, "data");
        final Type actualType = typeForName(typeName); 
        return context.deserialize(data, actualType);
    }
    private Type typeForName(final JsonElement typeElem) {
        try {
            return Class.forName(typeElem.getAsString());
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e);
        }
    }
    private JsonElement get(final JsonObject wrapper, String memberName) {
        final JsonElement elem = wrapper.get(memberName);
        if (elem == null) throw new JsonParseException("no '" + memberName + "' member found in what was expected to be an interface wrapper");
        return elem;
    }
}
