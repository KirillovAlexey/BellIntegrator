package com.company;

import com.readData.ReadFile;
import com.readData.XmlParse;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class GenerateReport {
    ReadFile readFile;
    XmlParse xmlParse;
    int countRow=0;


    static String delimetrColumn = "|";
    static String delimetrString = "-";
    StringBuilder number = new StringBuilder("Номер"); //numberSB.setLength(8);
    StringBuilder dates = new StringBuilder("Дата"); //dateSB.setLength(7);
    StringBuilder fio = new StringBuilder("ФИО"); //fioSB.setLength(7);

    OutputStreamWriter outputStreamWriter;

    public GenerateReport(ReadFile read, XmlParse xmlParse) {
        this.readFile = read;
        this.xmlParse = xmlParse;
        number.setLength(Integer.parseInt(xmlParse.getColumn().getColumns().get("Номер")));
        dates.setLength(Integer.parseInt(xmlParse.getColumn().getColumns().get("Дата")));
        fio.setLength(Integer.parseInt(xmlParse.getColumn().getColumns().get("ФИО")));
    }

    public void generate() throws IOException {
        outputStreamWriter = new OutputStreamWriter(new FileOutputStream("src/main/resources/result.tsv"), "utf16");
        readFile = new ReadFile();
        inputString(outputStreamWriter, number.toString(), dates.toString(), fio.toString());
        generateReport();

    }

    public void inputString(OutputStreamWriter outputStreamWriter, String num, String date, String fio) throws IOException {
        createHead(num,date,fio);
        outputStreamWriter.write(new String(new char[32]).replace("\0", delimetrString) + "\n");
        countRow++;
        //outputStreamWriter.write(new String(new char[32]).replace("\0", delimetrString) + "\n");
        countRow++;
        outputStreamWriter.flush();
    }

    public void generateReport() throws IOException {
        for (String[] str : readFile.getDataArray()) {
            String tempNum;
            String tempData;
            String tempFio;

            do {
                if (str[0].length() > 8) {
                    tempNum = str[0].substring(0, 8);
                    tempNum = String.format(" %-8s ", tempNum);
                    str[0] = str[0].substring(8);
                    outputStreamWriter.write(delimetrColumn + tempNum);
                }
                else if(str[0].length()<=8){
                    tempNum = String.format(" %-8s ", str[0]);
                    outputStreamWriter.write(delimetrColumn + tempNum);
                    str[0]="";
                    tempNum = str[0];
                }
                if (str[1].length() > 7) {
                    tempData = str[1].substring(0, 7);
                    tempData = String.format(" %-7s ", tempData);
                    str[1] = str[1].substring(7);
                    outputStreamWriter.write(delimetrColumn + tempData);
                }
                else if(str[1].length()<=7){
                    tempData = String.format(" %-7s ", str[1]);
                    outputStreamWriter.write(delimetrColumn + tempData);
                    str[1] = "";
                    tempData = str[1];

                }
                if (str[2].length() > 7) {
                    str[2] = str[2].trim();
                    tempFio = str[2].substring(0, 7);
                    tempFio = String.format(" %-7s ", tempFio);
                    str[2] = str[2].substring(7);
                    outputStreamWriter.write(delimetrColumn + tempFio + delimetrColumn);
                }
                else if(str[2].length()<=7){
                    tempFio = String.format(" %-7s ", str[2]);
                    outputStreamWriter.write(delimetrColumn + tempFio + delimetrColumn);
                    str[2]="";
                    tempFio=str[2];
                }
                outputStreamWriter.write("\n");
                outputStreamWriter.flush();
                countRow++;
                if(countRow%12==0){
                    outputStreamWriter.write("~\n");
                    createHead(number.toString(),dates.toString(),fio.toString());
                }
            } while (str[0].length() >= 8 || str[1].length() >= 7 || str[2].length() >= 7);
            str[2] = str[2].trim();
            if(str[0].length()!=0 || str[1].length()!=0 || str[2].length()!=0) {
                outputStreamWriter.write(delimetrColumn + String.format(" %-8s ", str[0])
                        + delimetrColumn + String.format(" %-7s ", str[1])
                        + delimetrColumn + String.format(" %-7s ", str[2]) + delimetrColumn + "\n");
                countRow++;
            }
            outputStreamWriter.write(new String(new char[32]).replace("\0", delimetrString));
            countRow++;
            outputStreamWriter.write("\n");
            countRow++;
            outputStreamWriter.flush();
            if(countRow%12==0){
                outputStreamWriter.write("~\n");
                createHead(number.toString(),dates.toString(),fio.toString());
            }
        }
    }

    public void createHead(String num,String date,String fio) throws IOException {
        outputStreamWriter.write(delimetrColumn + " " + num + " " + delimetrColumn);
        outputStreamWriter.write(" " + date + " " + delimetrColumn);
        outputStreamWriter.write(" " + fio + " " + delimetrColumn + "\n");
        //outputStreamWriter.write(new String(new char[32]).replace("\0", delimetrString) + "\n");
        outputStreamWriter.flush();
    }
}
