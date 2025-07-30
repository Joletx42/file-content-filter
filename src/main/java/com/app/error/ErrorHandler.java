package com.app.error;

import java.io.IOException;

public class ErrorHandler {
    public static void handle(Exception error) {
        System.out.println("ERROR: " + error.getMessage());
    }

    public static void noFile(String file) {
        System.out.println(
                "ERROR: Такого файла не существует: " + file);
    }

    public static void unknownParam(String param) {
        System.out.println(
                "ERROR: Неизвестный параметр: " + param);
    }

    public static void noValueForParam(String currentElem) {
        System.out.println(
                "ERROR: Отсутствует значение для параметра: " + currentElem);

    }

    public static void unknownArgumentType(String arg) {
        System.out.println(
                "ERROR: Неизвестный тип аргумента: " + arg);
    }

    public static void readerError(IOException e, String fileName) {
        System.out.println(
                "ERROR: Ошибка чтения файла " + fileName
                        + ": "
                        + e);
    }

    public static void writerError(IOException e, String fileName) {
        System.out.println(
                "ERROR: Ошибка записи в файл " + fileName
                        + ": "
                        + e);
    }

    public static void makeDirsError(String absolutePath) {
        System.out.println(
                "ERROR: Не удалось создать папки: " + absolutePath);
    }

    public static void emptyArgs() {
        System.out.println(
                "Использование: "
                        + "java -jar .\\target\\util.jar [ОПЦИИ]... [ВХОДНЫЙ ФАЙЛЫ]...\n"
                        + "Для большей информации: "
                        + "\'java -jar .\\\\target\\\\util.jar -h\'");
    }

    public static void emptyFile(String fileName) {
        System.out.println("ERROR: Файл " + fileName + " пуст");
    }

    public static void printHelp() {
        System.out.println(
                "Описание: "
                        + "\n"
                        + "Утилита принимает на вход файлы,\n"
                        + "в которых по строкам содержатся данные разных типов (int, float, строки).\n"
                        + "Она фильтрует эти строки по типам и записывает их в отдельные выходные файлы:\n"
                        + "integers.txt, floats.txt и strings.txt.\n\n"
                        + "Использование: "
                        + "java -jar .\\target\\util.jar [OPTION]... [FILE]...\n"
                        + "Где: "
                        + "\n"
                        + "  -jar .\\target\\util.jar запускает приложение из готового jar-файла, расположенного в папке target.\n\n"
                        + "  [OPTION] - дополнительные параметры, которые могут включать:\n"
                        + "    -s\t\tкраткая статистика\n"
                        + "    -f\t\tполная статистика\n"
                        + "    -a\t\tрежим добавления в выходные файлы (вместо перезаписи)\n"
                        + "    -o [PATH]\tпуть для выходных файлов\n"
                        + "    -p [PREFIX]\tпрефикс имен выходных файлов\n\n"
                        + "  [FILE] - список входных файлов с данными для обработки\n\n"
                        + "Пример: "
                        + "\n"
                        + "  java -jar .\\target\\util.jar -s -a -p sample- in1.txt in2.txt");
    }
}
