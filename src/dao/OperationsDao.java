package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.PageSize;
//import com.itextpdf.text.pdf.PdfContentByte;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfPTableEvent;
//import com.itextpdf.text.pdf.PdfWriter;

import Modele.Client;
import Modele.Operations;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OperationsDao {

	Connection laConnection = DAO.initConnection();
	Float montantCpte = new Float(0.1);
	Float sommeCred = new Float(0.1);
	Float sommeDeb = new Float(0.1);

	// public Client renseignerInfosClient(String numCpte){
	//
	// Client cust= new Client();
	//
	// try
	// {
	// Statement st= laConnection.createStatement();
	// ResultSet rs= st.executeQuery("SELECT numClt, nomClt, prenomClt, villeClt
	// FROM tb_client WHERE numClt IN ( SELECT numClt FROM tb_compte WHERE
	// numCpte="+numCpte+")");
	//
	// if (rs.first()){
	//
	// String numClient=rs.getString("numClt");
	// String nomClt=rs.getString("nomClt");
	// String prenomClt=rs.getString("prenomClt");
	// String villeClt=rs.getString("villeClt");
	//
	// cust= new Client(numClient, nomClt, prenomClt, villeClt);
	// }
	//
	//
	// }
	//
	// catch(SQLException e){
	//
	// e.printStackTrace();
	// }
	//
	//
	//
	// return cust;
	//
	// }
	// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	public ObservableList<String> renvoyerListeOpe() {

		ObservableList<String> listOpe = FXCollections.observableArrayList();

		try {
			Statement st = laConnection.createStatement();

			ResultSet rs = st.executeQuery("SELECT * FROM tb_operations");

			while (rs.next()) {

				// je recupere le contenu de la colonne numOpe, montantOpe etc
				String numOpe = rs.getString("numOpe");

				// on parcours chacune des lignes de la requete, je recupère
				// chacune des colonnes dans un attribut , et j'en crée un objet

				listOpe.add(numOpe);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return listOpe;

	}

	// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	public ArrayList<Operations> trouverOperations(String numCpte) {

		ArrayList<Operations> listOpe = new ArrayList<Operations>();
		CompteDAO cptDao = new CompteDAO();
		Operations uneOpe = new Operations();
		System.out.println("operations DAO ok");

		try {
			Statement st = laConnection.createStatement();

			ResultSet rs = st.executeQuery("SELECT * FROM tb_operations WHERE numCpte=" + numCpte);
			System.out.println("ça marche: SELECT * FROM tb_operations WHERE numCpte=" + numCpte);

			while (rs.next()) {

				// je recupere le contenu de la colonne numOpe, montantOpe etc
				String numOpe = rs.getString("numOpe");
				Float montantOpe = rs.getFloat("montantOpe");
				LocalDate dateOpe = rs.getDate("dateOpe").toLocalDate();
				Integer codeType = rs.getInt("codeType");
				Float credit = rs.getFloat("credit");
				Float debit = rs.getFloat("debit");

				// on parcours chacune des lignes de la requete, je recupère
				// chacune des colonnes dans un attribut , et j'en crée un objet
				uneOpe = new Operations(numOpe, montantOpe, dateOpe, codeType);
				listOpe.add(uneOpe);
				
			}
			/*je parcours la liste des opérations trouvées sur le compte, pour chacune je recupère le code du type d'opération. 
			 * Si le code est égal à 1 c'est qu'il s'agit d'un crédit, sinon c'est un débit. J'en fait la somme, afin de calculer 
			 * le montant total disponible sur le compte
			*/
			for (int i = 0; i < listOpe.size(); i++) {
				
				if (listOpe.get(i).getCodeType() == 1) {
					
					sommeCred = listOpe.get(i).getMontantOpe() + sommeCred;
					
				} else {

					sommeDeb = listOpe.get(i).getMontantOpe() + sommeDeb;

				}
			}
			montantCpte = sommeCred - sommeDeb;
			cptDao.actualiserSolde(montantCpte, numCpte);

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return listOpe;

	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------------

	public Boolean ajouterOpe(Operations o, String numCpte) {

		Boolean ok = true;
		try {
			Statement st = laConnection.createStatement();
			Float montantOpe = o.getMontantOpe();
			LocalDate dateOpe = o.getDateOpe();
			Integer typeOpe = o.getCodeType();
			
			System.out.println("INSERT INTO tb_operations (montantOpe, dateOpe, numCpte,codeType) values('"
					+ montantOpe + "','" + dateOpe + "','" + numCpte + "','" + typeOpe
					+ ")");

			
			st.executeUpdate("INSERT INTO tb_operations (montantOpe, dateOpe, numCpte,codeType) values('"
					+ montantOpe + "','" + dateOpe + "','" + numCpte + "','" + typeOpe
					+ "')");

		} catch (SQLException e) {

			e.printStackTrace();
			ok = false;
		}

		return ok;

	}

	// --------------------------------------------------------------------------------------------------------------------------------------------------------------------

	public void supprimerOpe(String numOpe) {

		try {
			Statement st = laConnection.createStatement();
			st.executeUpdate("DELETE FROM tb_operations WHERE numOpe='" + numOpe + "'");

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// -------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	public void updateOpe(Operations o) {

		String numOpe = o.getNumOpe();
		Float montantOpe = o.getMontantOpe();
		LocalDate dateOpe = o.getDateOpe();
		Integer typeOpe = o.getCodeType();
		

		try {
			Statement st = laConnection.createStatement();
			st.executeUpdate(
					"UPDATE tb_operations set montantOpe='" + montantOpe + "'," + "dateOpe='" + dateOpe + "',typeOpe='"
							+ typeOpe +"'WHERE numOpe='" + numOpe + "'");

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	// -------------------------------------------------------------------------------------------------
	// public void genererPdf(String numCpte, String nomFichier){
	// OperationsDao ope= new OperationsDao();
	// try{
	// Statement st= laConnection.createStatement();
	// Document document= new Document(PageSize.A4.rotate());
	// PdfWriter.getInstance(document, new FileOutputStream(nomFichier));
	// document.open();
	// ArrayList <Operations> listOpe= ope.trouverOperations(numCpte);
	// PdfPTableEvent event = new OperationsDao();
	//
	// for(Operations op:listOpe){
	// PdfPTable table = getTable(laConnection, op);
	// table.setTableEvent(event);
	// document.add(table);
	// document.newPage();
	// }
	//
	// document.close();
	//
	// }catch(Exception e){
	//
	// e.printStackTrace();
	//
	// }
	// }
	// -----------------------------------------------------------------------------------------------------

	public Float getSoldeCpte() {

		return montantCpte;
	}

	// -----------------------------------------------------------------------------------------------------

	// @Override
	// public void tableLayout(PdfPTable arg0, float[][] arg1, float[] arg2, int
	// arg3, int arg4, PdfContentByte[] arg5) {
	// // TODO Auto-generated method stub
	//
	// }

}
