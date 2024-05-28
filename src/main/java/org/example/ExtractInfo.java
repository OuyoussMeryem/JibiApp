package org.example;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class ExtractInfo {
    public static void main(String[] args) {
        try {
            // Spécifier le chemin vers le dossier tessdata
            String tessDataPath = "C:\\Users\\multitech\\Desktop\\projet\\test_automation\\tessdata";
            System.setProperty("TESSDATA_PREFIX", tessDataPath);

            // Extraire le texte en arabe et en français
            String arabicText = extractText("C:\\chemin\\vers\\votre\\image.png", "ara");
            String frenchText = extractText("C:\\chemin\\vers\\votre\\image.png", "fra");

            // Afficher les informations extraites
            System.out.println("Texte en arabe : " + arabicText);
            System.out.println("Texte en français : " + frenchText);
        } catch (TesseractException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour extraire le texte à partir d'une image avec Tesseract
    private static String extractText(String imagePath, String language) throws TesseractException {
        ITesseract instance = new Tesseract();
        return instance.doOCR(new File(imagePath));
    }
}
