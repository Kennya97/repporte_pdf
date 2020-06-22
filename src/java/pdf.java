
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

@WebServlet(urlPatterns = {"/pdf"})
public class pdf extends HttpServlet {


protected void processRequest(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
response.setContentType("application/pdf");


OutputStream out = response.getOutputStream();

try {
try{

Connection con = null;
Statement st= null;
ResultSet rs = null;

Class.forName("com.mysql.jdbc.Driver");
con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_inventario", "root",  " ");
st = con.createStatement();
rs= st.executeQuery("select * from  tb_categoria");


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
Do.add(new Phrase("Esto es un ejemplo de pdf de la tabla categoria",font_parrafo));
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

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}




