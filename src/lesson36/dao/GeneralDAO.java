package lesson36.dao;

import lesson36.exception.DAOException;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public abstract class GeneralDAO<T>{

    Set<String[]> readFromFile(String path) throws DAOException{
        Set<String[]> result = new HashSet<>();
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            while ((line = br.readLine()) != null)
                result.add(splitLine(line));
        } catch (FileNotFoundException e){
            throw new DAOException("Reading from file error: file "+path+" does not exist");
        } catch (IOException e){
            throw new DAOException("Reading from file "+path+" failed");
        }
        return result;
    }

    T writeToFile(T t, String path) throws DAOException{
        try(BufferedReader br = new BufferedReader(new FileReader(path)); BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))){
            if(br.readLine() != null)
                bw.append("\r\n");
            bw.append(String.valueOf(randomId())).append(", ").append(t.toString());
        } catch (FileNotFoundException e) {
            throw new DAOException("Writing to file error: file "+path+" does not exist");
        } catch (IOException e){
            throw new DAOException("Writing to file error: can`t save "+t.toString()+" to file "+path);
        }
        return t;
    }

    void deleteFromFileById(long id, String path) throws DAOException{
        StringBuffer tempData = new StringBuffer();
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            while((line = br.readLine()) != null) {
                if (!Long.valueOf(line.split(",")[0]).equals(id)) {
                    tempData.append(line);
                    tempData.append("\r\n");
                }
                tempData.replace(tempData.length() - 1, tempData.length(), "");
            }
        } catch (FileNotFoundException e) {
            throw new DAOException("Deleting from file error: file "+path+" does not exist");
        } catch (IOException e){
            throw new DAOException("Deleting from file error: can`t delete from file "+path);
        }

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path), false))){
            bw.append(tempData);
        } catch (IOException e){
            throw new DAOException("Deleting from file "+path+" failed");
        }
    }

    String[] getDataById(long id, String path) throws DAOException{
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            while ((line = br.readLine()) != null)
                if(splitLine(line)[0].equals(String.valueOf(id)))
                    return splitLine(line);
        } catch (FileNotFoundException e){
            throw new DAOException("Reading from file error: file "+path+" does not exist");
        } catch (IOException e){
            throw new DAOException("Reading from file "+path+" failed");
        }
        return null;
    }

    Set<String[]> getDataByElement(int elementNumber, String elementValue, String path) throws DAOException{
        Set<String[]> result = new HashSet<>();
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            while ((line = br.readLine()) != null)
                if(splitLine(line)[elementNumber].equals(elementValue))
                    result.add(splitLine(line));
        } catch (FileNotFoundException e){
            throw new DAOException("Reading from file error: file "+path+" does not exist");
        } catch (IOException e){
            throw new DAOException("Reading from file "+path+" failed");
        }
        return result;
    }

    private long randomId(){
        return ThreadLocalRandom.current().nextLong(Long.MAX_VALUE);
    }

    private String[] splitLine(String line){
        return line.split(",");
    }
}