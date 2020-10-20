package com.ufvmobile.pvanet.utils;


import com.ufvmobile.pvanet.R;

/**
 * Class to control what icon should be shown on screen
 */
public class IconResolver {

    public static String EXTENSION_PDF = ".pdf";
    public static String EXTENSION_XLS = ".xls";
    public static String EXTENSION_XLSX = ".xlsx";
    public static String EXTENSION_DOC = ".doc";
    public static String EXTENSION_DOCX = ".docx";
    public static String EXTENSION_PPT = ".ppt";
    public static String EXTENSION_PPTX = ".pptx";
    public static String EXTENSION_PHP = "php#";
    public static String EXTENSION_ZIP = ".zip";
    public static String EXTENSION_RAR = ".rar";
    public static String EXTENSION_AVI = ".avi";
    public static String EXTENSION_MP3 = ".mp3";
    public static String EXTENSION_MP4 = ".mp3";
    public static String EXTENSION_MOV = ".mp3";
    public static String EXTENSION_JPG = ".jpg";
    public static String EXTENSION_PNG = "png";
    public static String EXTENSION_TXT = ".txt";
    public static String EXTENSION_EXE = ".exe";

    /**
     *
     * @param fileName the {@link com.ufvmobile.pvanet.domain.model.Content#nomeArquivo}
     * @return The resource Id of the icon based on the file extension. Returns a default icon in
     * case of the file extension it's not recognized
     */
    public static int getIconByExtension(String fileName) {


        int icon = R.drawable.ic_default;
        int length = fileName.length();
        if (length >= 5) {

            String extension = fileName.substring(length - 5, length);

            if (extension.contains(EXTENSION_DOC) || extension.contains(EXTENSION_DOCX)) {
                icon = R.drawable.doc;
            } else if (extension.contains(EXTENSION_PPT) || extension.contains(EXTENSION_PPTX)) {
                icon = R.drawable.ppt;
            } else if (extension.contains(EXTENSION_PDF)) {
                icon = R.drawable.pdf;
            } else if (extension.contains(EXTENSION_XLS) || extension.contains(EXTENSION_XLSX)) {
                icon = R.drawable.xls;
            } else if (extension.contains(EXTENSION_ZIP)) {
                icon = R.drawable.zip;
            } else if (extension.contains(EXTENSION_RAR)) {
                icon = R.drawable.rar;
            } else if (extension.contains(EXTENSION_AVI) ||
                    extension.contains(EXTENSION_MOV) || extension.contains(EXTENSION_MP4)) {
                icon = R.drawable.video;
            } else if (extension.contains(EXTENSION_MP3)) {
                icon = R.drawable.audio;
            } else if (extension.contains(EXTENSION_JPG) || extension.contains(EXTENSION_PNG)) {
                icon = R.drawable.image;
            } else if (extension.contains(EXTENSION_TXT)) {
                icon = R.drawable.txt;
            } else if (extension.contains(EXTENSION_EXE)) {
                icon = R.drawable.exe;
            }

        }
        return icon;
    }


}
