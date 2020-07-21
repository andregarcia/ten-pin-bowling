package input;

import domain.Roll;
import exception.InputParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TenPinBowlingInputReader {

    public static List<Roll> readFile(String path){
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(path));
            List<String> lines = new ArrayList<String>();
            String line;
            while((line = reader.readLine()) != null){
                lines.add(line);
            }
            return readLines(lines);
        } catch (Exception e) {
            throw new InputParseException("Error parsing input file");
        }
    }

    protected static List<Roll> readLines(List<String> lines) throws Exception {
        List<Roll> rolls = new ArrayList<Roll>();
        for(String line : lines) {
            line = line.trim();
            if(line.isEmpty()) continue;
            String[] spl = line.split("\\s+");
            if(spl.length != 2) throw new InputParseException(String.format("Line contains formatting error: [%s]", line));
            String name = spl[0];
            String score = spl[1];
            if (score.equals("F")) {
                rolls.add(new Roll(name, 0, true));
            } else {
                Roll roll;
                try {
                    roll = new Roll(name, Integer.valueOf(score), false);
                } catch(Exception e){
                    throw new InputParseException(String.format("Could not create roll for player=[%s] score=[%s]", name, score));
                }
                rolls.add(roll);
            }
        }
        return rolls;
    }

}
