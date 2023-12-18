package Utilities;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;

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

import Building.Map;
import Building.Space;
public class LoadSave {
    private String workingDirectory;
    public LoadSave(){
        String currentDir = System.getProperty("user.dir");
        this.workingDirectory = currentDir + "/saves";
        
    }
    public ArrayList<Map> loadSaves(){
        ArrayList<Map> loadedMaps = new ArrayList<Map>();
        File dir = new File(this.workingDirectory);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                loadedMaps.add(load(child));
            }
        }
        return loadedMaps;
    }
    public Map load(File file){
        JsonReader jsonReader;
        Map loadedMap = null;
        try {
            jsonReader = new JsonReader(new FileReader(file));
            GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
            gsonBuilder.registerTypeAdapter(Space.class, new InterfaceAdapter<Space>());
            gsonBuilder.registerTypeAdapter(Color.class, new InterfaceAdapter<Color>());
            Gson gson = gsonBuilder.create();
            loadedMap = gson.fromJson(jsonReader, Map.class);
            createRelationsToLoadedMap(loadedMap);

        } catch (FileNotFoundException e) {
        }
        return loadedMap;
    }
    public void save(Space map){
        try {
            Writer writer = new FileWriter(new File(this.workingDirectory, map.getName() + ".json"));
            GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
            gsonBuilder.registerTypeAdapter(Space.class, new InterfaceAdapter<Space>());
            gsonBuilder.registerTypeAdapter(Color.class, new InterfaceAdapter<Color>());
            Gson gson = gsonBuilder.create();
            gson.toJson(map, writer);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void createRelationsToLoadedMap(Space map){
        for (Space space : map.getContents()) {
            space.addParent(map);
            createRelationsToLoadedMap(space);
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
