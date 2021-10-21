package controller;
//test
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Functions {

	public static  void setData(String linje){
		try{
			File fil = new File("data.txt");
			FileWriter writer = new FileWriter(fil,true);
			writer.write(linje+"\n");
			writer.flush(); // Sørger for at alt dataen fra buffer blir skrevet ned
			writer.close();
		}catch(FileNotFoundException e){

		}catch(Exception e){
			AlertClass.showAlert(e.toString());
		}
	}

	public  static void hentData(){

		try{
			BufferedReader buffread=new BufferedReader(new FileReader("data.txt"));
			String s="";
			while( (s=buffread.readLine()) !=null )
			{
				String data[]=new String[2];
				data=s.split(",");
				System.out.println(data[0]);
				//legger til dataen til listene
				DataController.liste.add( new GetSetData(data[0], data[1]));
				KontrollpanelController.liste.add( new GetSetData(data[0], data[1]));

			}
			buffread.close();
		}catch(FileNotFoundException e){
			AlertClass.showAlert(e.toString());
		}catch(Exception e){
			AlertClass.showAlert(e.toString());
		}

	}


	static void oppdaterData(String navn, String NyLinje, String table) {
		try {
			File LeseFil = new File(table + ".txt");
			File midlFil = new File("nyFil.txt");
			Path path = Paths.get(table + ".txt");

			BufferedReader reader = new BufferedReader(new FileReader(LeseFil));
			BufferedWriter writer = new BufferedWriter(new FileWriter(midlFil));

			String linjeTilFjerning = navn;
			String currentLinje;

			while ((currentLinje = reader.readLine()) != null) {

				if (currentLinje.equals(linjeTilFjerning)) {
					writer.write(NyLinje + System.getProperty("line.separator"));
				} else {
					writer.write(currentLinje + System.getProperty("line.separator"));
				}
			}
			writer.close();
			reader.close();
			Files.delete(path);
			boolean successful = midlFil.renameTo(LeseFil);
			System.out.println(successful);
		} catch (FileNotFoundException e) {

		} catch (Exception e) {
			AlertClass.showAlert(e.toString());
		}
	}



	static  void fjernData(String line, String table){
		try{
			//	Leser/skriver av dataen og en bufferreader
			File LesFil = new File(table+".txt");
			File midlFil = new File("nyFil.txt");
			Path path=Paths.get(table+".txt");

			BufferedReader reader = new BufferedReader(new FileReader(LesFil));
			BufferedWriter writer = new BufferedWriter(new FileWriter(midlFil));

			String linjeTilFjerning = line;
			String currentLinje;

			while((currentLinje = reader.readLine()) != null) {

				if(currentLinje.startsWith(linjeTilFjerning)) continue;
				writer.write(currentLinje + System.getProperty("line.separator"));
			}
			writer.close();
			reader.close();
			Files.delete(path);
			boolean successful = midlFil.renameTo(LesFil);
			System.out.println(successful);
		}catch(FileNotFoundException e){

		}catch(Exception e){
			AlertClass.showAlert(e.toString());
		}
	}


	public static boolean tallFinnes(String sample) //sjekker om tastet data er ugyldig
	{
		char[] chars = sample.toCharArray();
		StringBuilder sb = new StringBuilder();
		for(char c : chars){
			if(Character.isDigit(c)){
				sb.append(c);
			}
		}
		if(sb.length()<=0){
			return  false;
		}else {
			return  true;
		}
	}


}
