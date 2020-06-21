package com.company;

import com.readData.ReadFile;
import com.readData.XmlParse;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

class GenerateReport {
    private ReadFile readFile;
    private XmlParse xmlParse;
    private OutputStreamWriter outputStreamWriter;
    private int countRow = 0;//счетчик строк
    private final int lNum;//длина Номера
    private final int lDate;//длина Даты
    private final int lFio;//длина ФИО

    String tempNum;
    String tempData;
    String tempFio;

    int pageWidth;
    int pageHeight;

    private static final String PATH = "src\\main\\resources\\";
    private static final String SEPARATOR_COLUMN = "|";
    private static final String SEPARATOR_LINE = "-";
    private final StringBuilder number = new StringBuilder("Номер");
    private final StringBuilder dates = new StringBuilder("Дата");
    private final StringBuilder fio = new StringBuilder("ФИО");

    public GenerateReport(ReadFile read) throws IOException, SAXException, ParserConfigurationException {
        this.readFile = read;
        //xmlParse = new XmlParse();
        pageWidth = Integer.parseInt(xmlParse.getPage().getWidth());
        pageHeight = Integer.parseInt(xmlParse.getPage().getHeight());
        lNum = Integer.parseInt(xmlParse.getColumn().getColumns().get("Номер"));
        lDate = Integer.parseInt(xmlParse.getColumn().getColumns().get("Дата"));
        lFio = Integer.parseInt(xmlParse.getColumn().getColumns().get("ФИО"));

        number.setLength(Integer.parseInt(xmlParse.getColumn().getColumns().get("Номер")));
        dates.setLength(Integer.parseInt(xmlParse.getColumn().getColumns().get("Дата")));
        fio.setLength(Integer.parseInt(xmlParse.getColumn().getColumns().get("ФИО")));
    }


    public void generate(String param) throws IOException {
        outputStreamWriter = new OutputStreamWriter(new FileOutputStream(PATH + param), StandardCharsets.UTF_16);
        //readFile = new ReadFile();
        createCap(number.toString(), dates.toString(), fio.toString());
        generateReport();
        footer();
    }

/*    public void generate() throws IOException{
        outputStreamWriter = new OutputStreamWriter(new FileOutputStream(PATH + "report.tsv"), StandardCharsets.UTF_16);
        createCap(number.toString(), dates.toString(), fio.toString());
        generateReport();
        footer();
    }*/

    //Генерация отчета
    private void generateReport() throws IOException {
        for (String[] str : readFile.getDataArray()) {
            // Запись строки в буффер, пока не закончится входная строка.
            do {
                //Отрезание строки "Номер", запись в буфер
                if (str[0].length() > lNum) {
                    tempNum = str[0].substring(0, lNum);
                    tempNum = String.format(" %-" + lNum + "s ", tempNum);
                    str[0] = str[0].substring(lNum);
                    outputStreamWriter.write(SEPARATOR_COLUMN + tempNum);
                } else if (str[0].length() <= lNum) {
                    tempNum = String.format(" %-" + lNum + "s ", str[0]);
                    outputStreamWriter.write(SEPARATOR_COLUMN + tempNum);
                    str[0] = "";
                }
                //Отрезание строки "Дата", запись в буфер
                if (str[1].length() > lDate) {
                    tempData = str[1].substring(0, lDate);
                    tempData = String.format(" %-" + lDate + "s ", tempData);
                    str[1] = str[1].substring(lDate);
                    outputStreamWriter.write(SEPARATOR_COLUMN + tempData);
                } else if (str[1].length() <= lDate) {
                    tempData = String.format(" %-" + lDate + "s ", str[1]);
                    outputStreamWriter.write(SEPARATOR_COLUMN + tempData);
                    str[1] = "";

                }
                //Отрезание строки "ФИО", запись в буфер
                if (str[2].length() > lFio) {
                    str[2] = str[2].trim();
                    tempFio = str[2].substring(0, lFio);
                    tempFio = String.format(" %-" + lFio + "s ", tempFio);
                    str[2] = str[2].substring(lFio);
                    outputStreamWriter.write(SEPARATOR_COLUMN + tempFio + SEPARATOR_COLUMN);
                } else if (str[2].length() <= lFio) {
                    tempFio = String.format(" %-" + lFio + "s ", str[2]);
                    outputStreamWriter.write(SEPARATOR_COLUMN + tempFio + SEPARATOR_COLUMN);
                    str[2] = "";
                }
                outputStreamWriter.write("\n");
                outputStreamWriter.flush();
                countRow++;

                //Вставка символа разделения страниц, печать заголовка
                if (countRow == pageHeight) {
                    outputStreamWriter.write("~\n");
                    createCap(number.toString(), dates.toString(), fio.toString());
                    countRow = 0;
                }
            } while (str[0].length() >= lNum || str[1].length() >= lDate || str[2].length() >= lFio);
            str[2] = str[2].trim();
            //Запись в файл всего что осталось от первоначальных строк(№,Дата, ФИО)
            if (str[0].length() != 0 || str[1].length() != 0 || str[2].length() != 0) {
                outputStreamWriter.write(SEPARATOR_COLUMN + String.format(" %-" + lNum + "s ", str[1])
                        + SEPARATOR_COLUMN + String.format(" %-" + lDate + "s ", str[1])
                        + SEPARATOR_COLUMN + String.format(" %-" + lFio + "s ", str[2]) + SEPARATOR_COLUMN + "\n");
                countRow++;
                outputStreamWriter.flush();
            }

            outputStreamWriter.write(new String(new char[Integer.parseInt(xmlParse.getPage().getWidth())])
                    .replace("\0", SEPARATOR_LINE) + "\n");
            countRow++;
            outputStreamWriter.flush();
            //Вставка символа разделения страниц, печать заголовка - "~"
            if (countRow == pageHeight) {
                outputStreamWriter.write("~\n");
                createCap(number.toString(), dates.toString(), fio.toString());
                countRow = 0;
            }
        }
    }

    // Создание заголовка
    private void createCap(String num, String date, String fio) throws IOException {
        outputStreamWriter.write(SEPARATOR_COLUMN + " " + num + " " + SEPARATOR_COLUMN);
        outputStreamWriter.write(" " + date + " " + SEPARATOR_COLUMN);
        outputStreamWriter.write(" " + fio + " " + SEPARATOR_COLUMN + "\n");
        countRow++;
        outputStreamWriter.write(new String(new char[Integer.parseInt(xmlParse.getPage().getWidth())])
                .replace("\0", SEPARATOR_LINE) + "\n");
        countRow++;
        outputStreamWriter.flush();
    }

    //Подвал
    public void footer() throws IOException {
        outputStreamWriter.write("\n\nДля Запуска использовать следующую команду:\njava -cp target\\classes com.company.Main settings.xml source-data.tsv report.tsv");
        outputStreamWriter.flush();
    }
}
