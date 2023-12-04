package AoC2022;

import Common.GetScanner;

import java.util.*;

public class Prob7 {
    public static void main(String[] args) {

        Scanner myScanner = GetScanner.get(2022,"Day-7");
        ArrayList<Directory> directories = new ArrayList<>();

        Directory workingDir = new Directory("/", new Directory(""));
        directories.add(workingDir);
        myScanner.nextLine();

        while (myScanner.hasNext()){
            String line = myScanner.nextLine();
            ArrayList<String> splitLine = new ArrayList<>(Arrays.asList(line.split(" ")));
            if(line.charAt(0) == '$'){
                if (line.charAt(2) == 'c') {
                    if (splitLine.get(2).equals("..")) {
                        workingDir = workingDir.getParent();
                    } else {
                        workingDir = workingDir.getSubDirectory(splitLine.get(2));
                    }
                }
            }else if(line.charAt(0) == 'd'){
                Directory newDir = new Directory(splitLine.get(1),workingDir);
                workingDir.addDir(newDir);
                directories.add(newDir);
            }else {
                workingDir.addFile(new File(Long.parseLong(splitLine.get(0)),splitLine.get(1)));
            }
        }
        directories.get(0).getTotalSize();
        System.out.println(partOne(directories));
        System.out.println(partTwo(directories));

    }
    public static long partTwo(ArrayList<Directory> directories){

        long spaceNeeded = 30000000 - (70000000 - directories.get(0).getTotalSize());
        ArrayList<Long> deleteList = new ArrayList<>();

        for(Directory dir: directories){if (dir.getTotalSize() >= spaceNeeded){deleteList.add(dir.getTotalSize());}}

        deleteList.sort(null);
        return deleteList.get(0);
    }
    public static long partOne(ArrayList<Directory> directories){

        long sum = 0;
        for (Directory dir: directories){
            if (dir.totalSize < 100000L){
                sum += dir.totalSize;
            }
        }
        return sum;
    }
    static class Directory{

        ArrayList<Directory> subDirectories;
        ArrayList<File> files;
        String name;
        Directory parent;
        long totalSize;



        public Directory(String name){this.name = name;}
        public Directory(String name, Directory parent) {
            this.name = name;
            subDirectories = new ArrayList<>();
            files = new ArrayList<>();
            this.parent = parent;
            totalSize = 0;
        }

        @Override
        public boolean equals(Object o){
            if(o.getClass() != Directory.class){return false;}
            else{
                Directory dirO = (Directory) o;
                return dirO.getName().equals(this.getName());
            }
        }

        public long getTotalSize(){
            if(totalSize == 0) {
                for (Directory dir : subDirectories) {
                    totalSize += dir.getTotalSize();
                }
                for (File file : files) {
                    totalSize += file.size;
                }
            }
            return totalSize;
        }

        public ArrayList<Directory> getSubDirectories() {
            return subDirectories;
        }

        public Directory getSubDirectory(String name) {
            return subDirectories.get(subDirectories.indexOf(new Directory(name)));
        }

        public Directory getParent() {
            return parent;
        }

        public ArrayList<File> getFiles() {
            return files;
        }

        public String getName() {
            return name;
        }

        public void addFile(File file){files.add(file);}
        public void addDir(Directory dir){subDirectories.add(dir);}
    }
    static class File{
        String name;
        long size;

        public File(long size, String name) {
            this.name = name;
            this.size = size;
        }
    }
}


