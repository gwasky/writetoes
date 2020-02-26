package org.cs;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;
import java.util.Date;

/**
 * Created by Gibson on 1/28/20.
 */
public class Main {

    private static int NUM_OF_PROCESSES;

    private static String CDR_FILE_LOC;

    //private static final String CDR_FILE_LOC = "D:\\projects\\data\\";

    // private static final String CDR_FILE_LOC = "/data7/amtransactions/transactions/2015";

    public static void main(String[] args) throws IOException {

        CDR_FILE_LOC = args[0];

        NUM_OF_PROCESSES = Integer.parseInt(System.getProperty("NUM_PROC", "20"));

        try {

            ExecutorService executorService = Executors.newFixedThreadPool(NUM_OF_PROCESSES);

            File file = new File(CDR_FILE_LOC);

            File[] listFiles = file.listFiles();

            for (File file_to_process : listFiles) {

                System.out.println(file_to_process.getName());

                Stream<String> stream = GZIPFiles.lines(Paths.get(CDR_FILE_LOC + file_to_process.getName()));

                stream.forEach(line -> {

                    String[] splits = line.split("\\|~");

                    // System.out.println("t-> " +  splits[0]);

                    if (splits.length == 11) {

                        String formatedDate = stringToDate1(splits[0]);
                        Double grossAmount = Double.parseDouble((splits[7]));
                        Double feeAmount = Double.parseDouble(splits[8]);
                        Double srcEndBalance = Double.parseDouble(splits[9]);
                        Double tgtEndBalance = Double.parseDouble(splits[10]);
                        Record record = new Record(formatedDate, splits[1], splits[2], splits[3],
                                splits[4], splits[5], splits[6], grossAmount, feeAmount, srcEndBalance, tgtEndBalance);

                        // record.toString();

                        try {

                            executorService.execute(new ImportIntoES(record));

                        } catch (IOException e) {

                            e.printStackTrace();
                            System.exit(1);
                        }
                    }
                });

            }

            executorService.shutdown();

        } catch (Exception ex) {

            ex.printStackTrace();

        }
    }

    public static DateTime stringToDate(String date) {

        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MMM-yyyy HH:mm:ss");

        DateTime dateTime = DateTime.parse(date, formatter);

        return dateTime;

    }

    public static String stringToDate1(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MMM-yyyy HH:mm:ss");
            Date dated = DateTime.parse(date, formatter).toDate();
//            System.out.println( DateTime.parse(date, formatter).toDate());
//            String dateTime = DateTime.parse(date, formatter).toDate().toString();
//            Date dated = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(dateTime);
//            return dated;
            String newstring = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(dated);
            // System.out.println("Formated -> " + newstring);
            return newstring;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
