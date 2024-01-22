package com.cmm.utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Date;

public class CommonUtils {
    public static String saveBase64Image(String base64Image, String filePath) {
        String savedFilePath = "";

        try {
            String[] parts = base64Image.split(",");
            String base64Data = parts[1];

            byte[] decodedBytes = Base64.getDecoder().decode(base64Data);

            File file = new File(filePath);
            File parentDir = file.getParentFile();
            if (!parentDir.exists() && !parentDir.mkdirs()) {
                throw new IllegalStateException("Could not create directory: " + parentDir);
            }

            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(decodedBytes);
            }

            savedFilePath = file.getPath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return savedFilePath;
    }

    public static String encodeImageToBase64(String imagePath) {
        try {
            byte[] imageBytes = Files.readAllBytes(Path.of(imagePath));

            return Base64.getEncoder().encodeToString(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String getResponseDateAndTime(Timestamp dateAndTime){

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return dateFormat.format(new Date(dateAndTime.getTime()));
    }
}
