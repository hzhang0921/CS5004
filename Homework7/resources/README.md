# Shape Photo Album

a chunk of a code that succcessfully translates a .txt command file into either an HTML file or a viewable webpage using java Swing.

## Main Components

### Controller

- In this MVC model, it's not very complicated so the controller is only in charge of showing the correct viewer (either graphical or web) based on what the user calls from the command line.

### Model

- IAlbumModel -> The interface to the Albums themselves, which are made up of ISnapshot(s) as well as all methods to manage the ISnapshots themselves.
- IShape -> The interface for the Shapes that Snapshots contain.
- ISnapShot -> The interface for snapshots that record and store lists of all shapes and their corresponding data after a snapshot is called.

### Viewer

- GraphicalView -> uses Java swing to create a little GUI interface to access a given IAlbumModel and display it
- WebView -> takes an IAlbumModel and converts it to HTML so we can view it in our browsers.

### Command File Parser

- Command File Parser -> inputs a .txt file that is full of commands to create and modify snapshots with lists of shapes in it. Translates the commands into snapshot commands that can be added to an IAlbumModel. 

### Photo Album Main

- my main class is the final program that runs everything. The command line parser is also built into it with options for -in -view and -out. It also runs the graphical or web view based on those commands.

## New Design Changes

- Most notable one is changing AlbumModel to now use a hashmap to track the shapes within an AlbumModel. This helped to solve some of the issues I was having where the shapes would be generated in weird orders for the HTML file, ultimately causing the image to show up incorrectly.