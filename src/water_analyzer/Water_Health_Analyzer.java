/*Different water bodies around the world such as lakes, rivers, streams, oceans, etc
have a poor quality of water because of many factors. Such factors include
pollution, organic chemicals, microbes, etc. Water is life and so it is essential to
keep the quality of water to high standards for usage for both fauna and flora. This
is where you come in, you have been hired to program a water health analyzer
system that will analyze the water health of different water bodies the users enters
by calculating the Secchi Depth(in meters), the trophic state index value(using the
Secchi depth in meters), and the Hilsenhoff index value of the water body.
Additionally, the trophic state status and the Hilsenhoff index status also need to be
determined by the TSI and Hilsenhoff index values. The data values calculated need
to be stored in a tabular form in a pdf file. The data that needs to be stored in the
table are: the water body name, the water body type, the Secchi depth, the trophic
state index, and the Hilsenhoff index values. A QR code should be generated to
store these values as well. Finally, a title, the current date and time, the trophic
state index status, and the Hilsenhoff index status should be included as headings

To develop the water health analyzer program, the following guidelines need to be are:-

1. A class with the main method will need to be created.

2. The library packages(iText - lowagie and File) to create a pdf file and write to a pdf file
will need to be imported first.

3. The library package (Zxing) will also need to be imported to create a QRCode to store
the calculated data values for each water body.

4. The variables that'll be needed for the development of the program need to be declared as static within the class.

5. The generateQRCode method should be created with the code blocks within its body to generate the QR Code containing the results of the calculated data values
(Secchi depth, Trophic state index, and the Hilsenhoff biotic index).

6. A welcome message should be displayed to the user. Then, the user should be asked,
how many water bodies they would like to calculate the Secchi Depth(in meters), the
trophic state index(TSI), and the Hilsenhoff biotic index values for.

7. Appropriate loop structures should be implemented to allow the user to enter the
number of water bodies they would like to perform the calculations for. Users should
also be able to relaunch the water body health analyzer programmed system as many
times as they would like.

8. Using the correct formulas, steps on how to calculate the Secchi depth(in meters),
the trophic state index, and the Hilsenhoff biotic index values should be implemented
in the code.

9. The calculated data values should be inserted into a table in the pdf file that will
need to be stored at the path location entered by the user.
10. Conditional statements will need to be implemented in the code to determine the
trophic state index status and the Hilsenhoff biotic index status of the water bodies sampled.

11. Finally the results generated should be stored in the form of a QR code and stored
at a path location indicated by the user.


 */
package water_analyzer;

import java.util.*;
import java.awt.Color; //Because we wanna color the index of the tables.

//Beacuse we wanna be able to create the pdf file and we wanna be able to store the pdf file in a location given by the user and so we're writing the results to the pdf file.
import java.io.File; //deals with writing results on the pdf file and getting that displayed back from pdf file.
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;

//import the packages that will help us write information to the PDF and as well as customize the information written to the PDF like the font style, the size, the page size, giving it an orientation of landscape instead of portrait.
import com.lowagie.text.PageSize; //Use to chane the orientation of the page in pdf file from protrait to landscape.
import com.lowagie.text.Paragraph;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
//For table generation
import com.lowagie.text.Phrase; // bcz we're going to create a table.
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
//importing packages for generating QR Codes.
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter; //Encode massages in QR Code mean small black white boxes.
import com.google.zxing.common.BitMatrix;//So matrix of the QR code is is also encoded into ones and zero for the computer to understand.
import com.google.zxing.qrcode.QRCodeWriter;//writes out everything that's being generated as a matrix, which is the encoded massage that's the computer has calculated.

public class Water_Health_Analyzer {
	
	static String secchi;
	static String TSI_new;
	static String TSI_status = "";
	static double total_speciman = 0; //total no of speciman in the taxa
	static double total_product = 0; //total_product = total_speciman x tolerance value of taxa.
	static double HBI; // HBI = total_product/ total no of speciman in the sample;
	static String HBI_new;
	static String HBI_status = "" ;
	static String water_bodyname;
	static StringBuilder qr_result;
	static Date current_datetime;
	static DecimalFormat df;
	
	//Creating generateQRCode() method to generate QR Code of the results.
	@SuppressWarnings("deprecation")
	private static void generateQRCode(String text, int width, int height) throws Exception{
		QRCodeWriter qcobj = new QRCodeWriter(); //We need the QR code writer to be able to write the QR code.
		BitMatrix bmobj = qcobj.encode(text, BarcodeFormat.QR_CODE, width, height);//To encode the message that we want to hide inside the QR code. So it basically takes the message and encodes it into QR code.
		String path = "C:\\Users\\nasri\\Documents\\Study\\Projects\\Java\\result.png";//To specify the place where the QR code will be saved in the user's computer.
		MatrixToImageWriter.writeToFile(bmobj, "PNG", new File(path));//To change the encoded QR code matrix to an actual image.
	}
	
	public static void main(String[] args) throws Exception{
		Scanner text = new Scanner(System.in);
		Scanner number = new Scanner(System.in);

		//Inserting current date and time.
		Date current_datetime = new Date();
		//Keeping the answers upto two decimal places.
		df = new DecimalFormat("#.##");
		
		//Staring the main body of the program.
		
		System.out.println(ANSI_Colors.PURPLE_BOLD_BRIGHT+"***************************************************************************************");
		System.out.println("Welcome to Water Health Analyzer Program");
		System.out.println("***************************************************************************************");
		System.out.println();
		System.out.println(ANSI_Colors.RED_BOLD_BRIGHT+"Question:-  a grandfather and then comma two fathers and two sons Went to the movie theater \nand everyone brought one ticket each.\nSo how many tickets did they buy in total?(type your answer in letters)" );
		System.out.println("***************************************************************************************");
		System.out.println(ANSI_Colors.HIDDEN);
		String user_answer = text.nextLine();
		
		while(!user_answer.equalsIgnoreCase("three")) {
			System.out.println(ANSI_Colors.RESET);
			System.out.println(ANSI_Colors.RED_BOLD_BRIGHT+"Question:-  a grandfather and then comma two fathers and two sons Went to the movie theater \nand everyone brought one ticket each.\nSo how many tickets did they buy in total?(type your answer in letters)" );
			System.out.println("***************************************************************************************");
			System.out.println(ANSI_Colors.HIDDEN);
			user_answer = text.nextLine();
		}
		if(user_answer.equalsIgnoreCase("three")) {
			System.out.println(ANSI_Colors.RESET);
			System.out.println(ANSI_Colors.GREEN_BOLD_BRIGHT+"\uD83D\uDE03 Yay! you got it. So, you can now use this program");
			System.out.println();
			System.out.println(ANSI_Colors.CYAN_BOLD_BRIGHT+"How many water body areas would you like to analyze? ");
			int water_bodies = number.nextInt();
			System.out.println(ANSI_Colors.CYAN_BOLD_BRIGHT+"Please enter the location where you would like to store your PDF File ");
			String pdfFilePath = text.nextLine();
			OutputStream fos = new FileOutputStream(new File(pdfFilePath));
			Document document = new Document(PageSize.A4.rotate());
			PdfWriter writer = PdfWriter.getInstance(document, fos);
			//Table
			PdfPTable table = new PdfPTable(5);
			float widths[] = {3,3,3,3,3}; //Measured width in pixels.
			PdfPCell cell = new PdfPCell(new Phrase("Water Body Name"));
			cell.setBackgroundColor(Color.CYAN);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase("Water Body Type"));
			cell.setBackgroundColor(Color.CYAN);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase("Secchi Depth(meters)"));
			cell.setBackgroundColor(Color.CYAN);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase("TSI(Secchi Depth)"));
			cell.setBackgroundColor(Color.CYAN);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase("Hilsenhoff Biotic Index(HBI)"));
			cell.setBackgroundColor(Color.CYAN);
			table.addCell(cell);
			Phrase ph;
			qr_result = new StringBuilder();
			document.open();
			Font font_setting = FontFactory.getFont(FontFactory.TIMES,18f);
			document.add(new Paragraph("The Water Body Health Analyzer Results",font_setting));
			document.add(new Paragraph("\n"));
			document.add(new Paragraph(current_datetime.toString()));
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("Detailed Results:- "));
			document.add(new Paragraph("\n"));
			
			for(int i=1; i<= water_bodies; i++) {
				System.out.println(ANSI_Colors.BLUE_BOLD_BRIGHT+"Enter the name of the water body ");
				water_bodyname = text.nextLine();
				System.out.println("Enter the type of the water body(river, sea, stream, lake etc) ");
				String water_bodytype = text.nextLine();
				//Secchi Depth determination.
				System.out.println(ANSI_Colors.CYAN_BOLD_BRIGHT+"Enter the extinction depth(meters) ");
				double extinct_depth = number.nextDouble();
				System.out.println("Enter the eruption depth(meters) ");
				double erupt_depth = number.nextDouble();
				double secchi_depth = (extinct_depth + erupt_depth)/2;
				secchi = df.format(secchi_depth);
				//TSI determination.
				double TSI = 60 - 14.41 * Math.log(secchi_depth);
				TSI_new = df.format(TSI);
				//TSI Status determination.
				if(TSI >= 0 && TSI <= 40) {
					TSI_status = "Oligotrophic";
				}
				else if(TSI > 40 && TSI <= 50) {
					TSI_status = "Mesotrophic";
				}
				else if(TSI > 50 ) {
					TSI_status = "Eutrophic";
				}
				
				//Hilsenhoff Biotic Index(HBI) determination.
				System.out.println(ANSI_Colors.BLUE_BOLD_BRIGHT+"How many species did you sample in this area? ");
				int species = number.nextInt();
				
				for(int j=1; j<=species; j++) {
					System.out.println(ANSI_Colors.DARK_RED+"Enter the tolerance value of the species "+ j);
					double tolerance = number.nextDouble();
					System.out.println(ANSI_Colors.CYAN_BOLD_BRIGHT+"Enter the number of specimans sampled for species "+j);
					double speciman = number.nextDouble();
					double product = tolerance * speciman;
					total_speciman += speciman;
					total_product += product;
					HBI = total_product / total_speciman;	
				}
				HBI_new = df.format(HBI);
				//HBI Status determination.
				if(HBI >=0 && HBI <=3.75) {
					HBI_status = "The water quality is excellent, organic pollution is unlikely.";
				}
				else if(HBI >=3.76 && HBI <=4.25) {
					HBI_status = "The water quality is very good, possible slight organic pollution.";
				}
				else if(HBI >=4.26 && HBI <=5.00) {
					HBI_status = "The water quality is very good, Some organic pollution is probable, so some organic pollution is probable.";
				}
				else if(HBI >=5.01 && HBI <=5.75) {
					HBI_status = "The water quality is fair, fairly substantial pollution is likely, substantial pollution.";
				}
				else if(HBI >=5.76 && HBI <=6.50) {
					HBI_status = "The water quality is fairly poor, Substantial pollution is likely.";
				}
				else if(HBI >=6.51 && HBI <=7.25) {
					HBI_status = "The water quality is poor, Very substantial pollution is likely.";
				}
				else if(HBI >=7.26 && HBI <=10.0) {
					HBI_status = "The water quality is very poor, Severe organic pollution is likely.";
				}
				
				//Add contents under table in the Pdf File.
				cell = new PdfPCell();
				ph = new Phrase(water_bodyname);
				cell.addElement(ph);
				table.addCell(cell);
				
				cell = new PdfPCell();
				ph = new Phrase(water_bodytype);
				cell.addElement(ph);
				table.addCell(cell);
				
				cell = new PdfPCell();
				ph = new Phrase(secchi);
				cell.addElement(ph);
				table.addCell(cell);
				
				cell = new PdfPCell();
				ph = new Phrase(TSI_new);
				cell.addElement(ph);
				table.addCell(cell);
				
				cell = new PdfPCell();
				ph = new Phrase(HBI_new);
				cell.addElement(ph);
				table.addCell(cell);
				
				document.add(new Paragraph("Trophic State Index Result for Area "+i+" is:- "+ TSI_status));
				document.add(new Paragraph("\n"));
				document.add(new Paragraph("Hilsenhoff Biotic Index(HBI) Result for Area "+i+" is:- "+ HBI_status));
				document.add(new Paragraph("\n"));
				
				//Adding table to the Pdf File once everything is calculated.
				if(i==water_bodies) {
					document.add(table);
				}
				
				String result = "Water body name:- " + water_bodyname + "\nThe Secchi Depth in m is:- " + secchi + "\nThe Trophic State Index is:- " + TSI_new + "\nThe Hilsenhoff Biotic Index(HBI) is:- " + HBI_new + "\n";
				qr_result.append(result);
				
			}
			
			System.out.println(ANSI_Colors.GREEN_BOLD_BRIGHT+"Thank You!");
			generateQRCode(qr_result.toString(), 1250, 1250);
			document.close();
			writer.close();
			
		}
		
	}

}
