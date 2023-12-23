# Path Finder

A pathfinding framework for complex buildings that supports Map Design, Pathfinding to designed maps, User authentication for Map Designer tools and Feedback creation for users.


## User Types

### 1. Normal User
- No login required.
- Enter initial and desired locations to receive a path.
- Find the closest objects, such as water dispensers and restrooms.
- Provide feedback through the "Send Feedback" button.

### 2. Editor User
- Login with email and password.
- View and manage user feedback.
- Access the entire map through the "Map Tools" button.

### 3. Admin User
- Extensive privileges:
  - Add or remove admins and editors.
  - Full control over map editing and customization through "Map Tools."
  - Edit existing spaces, including buildings, floors, rooms, and objects.
  - Modify space attributes: location, names, colors, entrances, and sizes.
  - Add new spaces to the map.
  - Delete existing spaces from the map.
  - Zoom in/out and center the drawing panel.

## Usage

- Refer to the application interface for user-specific functionalities.
- Explore the map, find paths, and utilize the various tools based on your user role.
- Admins can add new spaces or delete existing ones through the "Map Tools" section.

## Installation

1. Clone the repository:
   bash
   $ git clone https://github.com/boraakoguz/path-finder.git

2. Enter the directory and run PathFinder.java:
   
## Dependencies

1. A firebase database for user authentication
2. firebase4j firebase manager library for java
3. gson to save the objects as json file
4. JSON for java

Dependencies 2-4 are included in the Libraries folder

## License

This project is licensed under [Your License] - see the [LICENSE](LICENSE) file for details.


