package org.example;


import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class TestImg {
    public static String crakImage(String filePath) throws TesseractException {
        File fileImage=new File(filePath);
            ITesseract instance=new Tesseract();
            instance.setDatapath("C:\\Users\\multitech\\Desktop\\projet\\test_automation\\tessdata");

            String result = instance.doOCR(fileImage);
            return result;

    }
    public static void main(String[] args) throws TesseractException {
        String textdata=TestImg.crakImage("C:\\Users\\multitech\\Desktop\\projet\\test_automation\\images\\cin_recto.png");
//        String textdata=TestImg.crakImage("C:\\Users\\multitech\\Desktop\\projet\\test_automation\\images\\verso.jpg");
//        String textdata=TestImg.crakImage("C:\\Users\\multitech\\Desktop\\projet\\test_automation\\images\\recto2.jpeg");


        System.out.println(textdata);
    }

}