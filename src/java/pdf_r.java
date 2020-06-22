

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = {"/pdf_r"})
public class pdf_r extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
 throws ServletException, IOException {
response.setContentType("application/pdf");


OutputStream out = response.getOutputStream();
String n = request.getParameter("txtn");

try {
try{

Connection con = null;
Statement st= null;
ResultSet rs = null;

Class.forName("com.mysql.jdbc.Driver");
con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_inventario", "root",  "");
st = con.createStatement();
rs= st.executeQuery("select * from  tb_categoria where  nom_categoria = '"+n+" ' ");


if(con!=null){
    
try {

Document Do = new Document();
PdfWriter.getInstance(Do, out);

Do.open();

Paragraph p1 = new Paragraph();
Font font_titulo = new Font(Font.FontFamily.HELVETICA,22,Font.BOLD, BaseColor.BLUE);
Do.add(new Phrase("Reporte de CATEGORIA",font_titulo));
p1.setAlignment(Element.ALIGN_CENTER);
p1.add(new Phrase(Chunk.NEWLINE));
p1.add(new Phrase(Chunk.NEWLINE));
Do.add(p1);



Paragraph p2 = new Paragraph();
Font font_parrafo = new Font(Font.FontFamily.TIMES_ROMAN, 14,Font.NORMAL, BaseColor.BLACK);
Do.add(new Phrase("Esto es un ejemplo de reporte pdf de la tabla categoria de mi base de dato bd_inventario",font_parrafo));
p2.setAlignment(Element.ALIGN_JUSTIFIED);
p2.add(new Phrase(Chunk.NEWLINE));
p2.add(new Phrase(Chunk.NEWLINE));
Do.add(p2);


PdfPTable tabla = new PdfPTable(3);

PdfPCell celda1 = new PdfPCell(new Paragraph ("id_categoria", FontFactory.getFont("Arial",12,Font.BOLD, BaseColor.RED)));
PdfPCell celda2 = new PdfPCell(new Paragraph ("nom_categoria", FontFactory.getFont("Arial",12,Font.BOLD,BaseColor.RED)));
PdfPCell celda3 = new PdfPCell(new Paragraph ("estado_categoria", FontFactory.getFont("Arial",12,Font.BOLD,BaseColor.RED)));


tabla.addCell(celda1);
tabla.addCell(celda2);
tabla.addCell(celda3);

while(rs.next()){

tabla.addCell(rs.getString(1));
tabla.addCell(rs.getString(2));
tabla.addCell(rs.getString(3));

}

Do.add(tabla);

Do.close();

}catch(Exception ex){

ex.getMessage();
}
}   


}catch(Exception ex){

ex.getMessage();
}
 

}finally{
out.close();

}
    
}
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
