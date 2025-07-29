package com.app.error;

import java.io.IOException;

import com.app.util.TerminalColors;

public class ErrorHandler {
    public static void handle(Exception error) {
        System.out.println(TerminalColors.colorize("ERROR: Неизвестная ошибка: ", TerminalColors.RED) + error);
    }

    public static void noFile(String file) {
        System.out.println(
                TerminalColors.colorize("ERROR: Такого файла не существует: ", TerminalColors.RED) + file);
    }

    public static void unknownParam(String param) {
        System.out.println(
                TerminalColors.colorize("ERROR: Неизвестный параметр: ", TerminalColors.RED) + param);
    }

    public static void unknownArgumentType(String arg) {
        System.out.println(
                TerminalColors.colorize("ERROR: Неизвестный тип аргумента: ", TerminalColors.RED) + arg);
    }

    public static void readerError(IOException e, String fileName) {
        System.out.println(
                TerminalColors.colorize("ERROR: Ошибка чтения файла ", TerminalColors.RED) + fileName
                        + TerminalColors.colorize(": ", TerminalColors.RED)
                        + e);
    }

    public static void writerError(IOException e, String fileName) {
        System.out.println(
                TerminalColors.colorize("ERROR: Ошибка записи в файл ", TerminalColors.RED) + fileName
                        + TerminalColors.colorize(": ", TerminalColors.RED)
                        + e);
    }

    public static void makeDirsError(String absolutePath) {
        System.out.println(
                TerminalColors.colorize("ERROR: Не удалось создать папки: ", TerminalColors.RED) + absolutePath);
    }

    public static void emptyArgs() {
        System.out.println(
                TerminalColors.colorize("Использование: ", TerminalColors.BLUE)
                        + "java -jar .\\target\\util.jar [ОПЦИИ]... [ВХОДНЫЙ ФАЙЛЫ]...\n"
                        + TerminalColors.colorize("Для большей информации: ", TerminalColors.BLUE)
                        + "\'java -jar .\\\\target\\\\util.jar -h\'");
    }

    public static void printHelp() {
        System.out.println(
                TerminalColors.colorize("Описание: ", TerminalColors.BLUE)
                        + "\n"
                        + "Утилита принимает на вход файлы,\n"
                        + "в которых по строкам содержатся данные разных типов (int, float, строки).\n"
                        + "Она фильтрует эти строки по типам и записывает их в отдельные выходные файлы:\n"
                        + "integers.txt, floats.txt и strings.txt.\n\n"
                        + TerminalColors.colorize("Использование: ", TerminalColors.BLUE)
                        + "java -jar .\\target\\util.jar [OPTION]... [FILE]...\n"
                        + TerminalColors.colorize("Где: ", TerminalColors.BLUE)
                        + "\n"
                        + "  -jar .\\target\\util.jar запускает приложение из готового jar-файла, расположенного в папке target.\n\n"
                        + "  [OPTION] - дополнительные параметры, которые могут включать:\n"
                        + "    -s\t\tкраткая статистика\n"
                        + "    -f\t\tполная статистика\n"
                        + "    -a\t\tрежим добавления в выходные файлы (вместо перезаписи)\n"
                        + "    -o [PATH]\tпуть для выходных файлов\n"
                        + "    -p [PREFIX]\tпрефикс имен выходных файлов\n\n"
                        + "  [FILE] - список входных файлов с данными для обработки\n\n"
                        + TerminalColors.colorize("Пример: ", TerminalColors.BLUE)
                        + "\n"
                        + "  java -jar .\\target\\util.jar -s -a -p sample- in1.txt in2.txt");
    }
}
