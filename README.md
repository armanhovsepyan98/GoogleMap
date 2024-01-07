# Android Jetpack Compose Google Map Project

Explore an interactive Android application developed with Jetpack Compose, offering a seamless experience with Google Maps integration. The project leverages Hilt for dependency injection, Room DB for efficient local data storage, and Compose Map for a modern approach to displaying maps.

![Screenshot_20231228_141302 (1)](https://github.com/armanhovsepyan98/GoogleMap/assets/102308110/bd94dc91-2998-41c7-869c-88a56892b54f)
![Screenshot_20231228_141347](https://github.com/armanhovsepyan98/GoogleMap/assets/102308110/4f0e8604-002b-4c0b-9c72-0e95a8dc4ff3)

## Features

- **Jetpack Compose UI**: Modern Android UI toolkit for building native UIs.
- **Google Maps Integration**: Display interactive maps powered by the Google Maps API.
- **Hilt**: Dependency injection library for Android, facilitating clean and modular code.
- **Room DB**: Local database for storing and retrieving data efficiently.
- **Compose Map**: A library for integrating Google Maps with Jetpack Compose.

## App Functionalities

1. **Add Markers**: Click and hold on the map to add markers at specific locations.
   
2. **Change Map Theme**: The app allows you to change the theme of the map for a personalized experience.

3. **Draw Polyline and Polygon with Markers**: Create polylines and polygons by connecting multiple markers on the map.

4. **Persistent Markers**: Markers added to the map will be saved in the local Room database. You can delete all markers and start fresh or leave the app and return to find your markers still present.

# Add Google Maps API Key:

- Obtain a Google Maps API key from the Google Cloud Console.
- Open the AndroidManifest.xml file.
- Find the following meta-data element inside the <application> tag:

````
<application>
    <!-- Other application elements -->
    <meta-data
        android:name="com.google.android.geo.API_KEY"
        android:value="" />
</application>
````

- Replace the empty string in android:value with your actual API key:


