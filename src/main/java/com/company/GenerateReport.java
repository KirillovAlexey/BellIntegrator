package com.company;

import com.readData.ReadFile;
import com.readData.XmlParse;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

class GenerateReport {
    private ReadFile readFile;
    private OutputStreamWriter outputStreamWriter;
    private int countRow = 0;//счетчик строк
    private final int lNum;//длина Номера
    private final int lDate;//длина Даты
    private final int lFio;//длина ФИО


    private static final String PATH = "src/main/resources/result.tsv";
    private static final String SEPARATOR_COLUMN = "|";
    private static final String SEPARATOR_LINE = "-";
    private final StringBuilder number = new StringBuilder("Номер");
    private final StringBuilder dates = new StringBuilder("Дата");
    private final StringBuilder fio = new StringBuilder("ФИО");

    public GenerateReport(ReadFile read, XmlParse xmlParse) {
        this.readFile = read;

        lNum = Integer.parseInt(xmlParse.getColumn().getColumns().get("Номер"));
        lDate = Integer.parseInt(xmlParse.getColumn().getColumns().get("Дата"));
        lFio = Integer.parseInt(xmlParse.getColumn().getColumns().get("ФИО"));

        number.setLength(Integer.parseInt(xmlParse.getColumn().getColumns().get("Номер")));
        dates.setLength(Integer.parseInt(xmlParse.getColumn().getColumns().get("Дата")));
        fio.setLength(Integer.parseInt(xmlParse.getColumn().getColumns().get("ФИО")));
    }


    public void generate() throws IOException {
        outputStreamWriter = new OutputStreamWriter(new FileOutputStream(PATH), StandardCharsets.UTF_16);
        readFile = new ReadFile();
        createCap(number.toString(), dates.toString(), fio.toString());
        generateReport();
    }

    //Генерация отчета
    private void generateReport() throws IOException {
        for (String[] str : readFile.getDataArray()) {
            String tempNum;
            String tempData;
            String tempFio;
            // Запись строки в буффер, пока не закончится входная строка.
            do {
                //Отрезание строки "Номер", запись в буфер
                if (str[0].length() > lNum) {
                    tempNum = str[0].substring(0, lNum);
                    tempNum = String.format(" %-8s ", tempNum);
                    str[0] = str[0].substring(lNum);
                    outputStreamWriter.write(SEPARATOR_COLUMN + tempNum);
                } else if (str[0].length() <= lNum) {
                    tempNum = String.format(" %-8s ", str[0]);
                    outputStreamWriter.write(SEPARATOR_COLUMN + tempNum);
                    str[0] = "";
                }
                //Отрезание строки "Дата", запись в буфер
                if (str[1].length() > lDate) {
                    tempData = str[1].substring(0, lDate);
                    tempData = String.format(" %-7s ", tempData);
                    str[1] = str[1].substring(lDate);
                    outputStreamWriter.write(SEPARATOR_COLUMN + tempData);
                } else if (str[1].length() <= lDate) {
                    tempData = String.format(" %-7s ", str[1]);
                    outputStreamWriter.write(SEPARATOR_COLUMN + tempData);
                    str[1] = "";

                }
                //Отрезание строки "ФИО", запись в буфер
                if (str[2].length() > lFio) {
                    str[2] = str[2].trim();
                    tempFio = str[2].substring(0, lFio);
                    tempFio = String.format(" %-7s ", tempFio);
                    str[2] = str[2].substring(lFio);
                    outputStreamWriter.write(SEPARATOR_COLUMN + tempFio + SEPARATOR_COLUMN);
                } else if (str[2].length() <= lFio) {
                    tempFio = String.format(" %-7s ", str[2]);
                    outputStreamWriter.write(SEPARATOR_COLUMN + tempFio + SEPARATOR_COLUMN);
                    str[2] = "";
                }

                outputStreamWriter.write("\n");
                outputStreamWriter.flush();

                countRow++;
                //Вставка символа разделения страниц, печать заголовка
                if (countRow % 12 == 0) {
                    outputStreamWriter.write("~\n");
                    createCap(number.toString(), dates.toString(), fio.toString());
                }
            } while (str[0].length() >= lNum || str[1].length() >= lDate || str[2].length() >= lFio);
            str[2] = str[2].trim();
            //Запись в файл всего что осталось от первоначальных строк(№,Дата, ФИО)
            if (str[0].length() != 0 || str[1].length() != 0 || str[2].length() != 0) {
                outputStreamWriter.write(SEPARATOR_COLUMN + String.format(" %-8s ", str[0])
                        + SEPARATOR_COLUMN + String.format(" %-7s ", str[1])
                        + SEPARATOR_COLUMN + String.format(" %-7s ", str[2]) + SEPARATOR_COLUMN + "\n");
                countRow++;
            }

            outputStreamWriter.write(new String(new char[32]).replace("\0", SEPARATOR_LINE));
            countRow++;
            outputStreamWriter.write("\n");
            countRow++;
            outputStreamWriter.flush();

            //Вставка символа разделения страниц, печать заголовка - "~"
            if (countRow % 12 == 0) {
                outputStreamWriter.write("~\n");
                createCap(number.toString(), dates.toString(), fio.toString());
            }
        }
    }

    // Создание заголовка
    private void createCap(String num, String date, String fio) throws IOException {
        outputStreamWriter.write(SEPARATOR_COLUMN + " " + num + " " + SEPARATOR_COLUMN);
        outputStreamWriter.write(" " + date + " " + SEPARATOR_COLUMN);
        outputStreamWriter.write(" " + fio + " " + SEPARATOR_COLUMN + "\n");
        outputStreamWriter.write(new String(new char[32]).replace("\0", SEPARATOR_LINE) + "\n");
        outputStreamWriter.flush();
    }
}
