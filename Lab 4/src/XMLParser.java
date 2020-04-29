import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class XMLParser {

    public ArrayList<HashMap<String, String>> readXML(String fileName)
    {
        ArrayList<HashMap<String, String>> queries = new ArrayList<>();
        try {
            File file = new File(fileName);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeLst = doc.getElementsByTagName("Borrowed_by");



            for (int s = 0; s < nodeLst.getLength(); s++) {

                Node fstNode = nodeLst.item(s);

                if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element sectionNode = (Element) fstNode;

                    NodeList memberIdElementList = sectionNode.getElementsByTagName("MemberID");
                    Element memberIdElmnt = (Element) memberIdElementList.item(0);
                    NodeList memberIdNodeList = memberIdElmnt.getChildNodes();
                    String memberID = ((Node) memberIdNodeList.item(0)).getNodeValue().trim();


                    NodeList secnoElementList = sectionNode.getElementsByTagName("ISBN");
                    Element secnoElmnt = (Element) secnoElementList.item(0);
                    NodeList secno = secnoElmnt.getChildNodes();
                    String isbn = ((Node) secno.item(0)).getNodeValue().trim();


                    NodeList codateElementList = sectionNode.getElementsByTagName("Checkout_date");
                    Element codElmnt = (Element) codateElementList.item(0);
                    NodeList cod = codElmnt.getChildNodes();
                    String checkoutDate = ((Node) cod.item(0)).getNodeValue().trim();


                    NodeList cidateElementList = sectionNode.getElementsByTagName("Checkin_date");
                    Element cidElmnt = (Element) cidateElementList.item(0);
                    NodeList cid = cidElmnt.getChildNodes();
                    String checkinDate = ((Node) cid.item(0)).getNodeValue().trim();
                    HashMap<String, String> query = new HashMap<String, String>();
                    if(checkoutDate.equals("N/A")){
                        query.put("query", "update BorrowedBy\nset CheckinDate = '" + getSQLDateFormat(checkinDate) +"'" +
                                "\nwhere MemberID = " + memberID + " and CheckinDate IS NULL and ISBN = '" + isbn + "';");
                        query.put("message", "CHECKIN: \n\tISBN: " + isbn + "\n\tMemberID: " + memberID + "\n\tDate: " + getSQLDateFormat(checkinDate) );



                    }
                    else{
                       query.put("query", "INSERT INTO BorrowedBy VALUES\n" +
                               "('" + isbn + "', " + memberID +", " + "'" + getSQLDateFormat(checkoutDate) + "'," + " Null);");
                       query.put("message", "CHECKOUT: \n\tISBN: " + isbn + "\n\tMemberID: " + memberID + "\n\tDate: " + getSQLDateFormat(checkoutDate));

                    }
                    queries.add(query);


                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return queries;
    }

    private static String getSQLDateFormat(String dateString) throws ParseException {
        SimpleDateFormat inFormatter = new SimpleDateFormat("mm/dd/yyy");
        Date date = inFormatter.parse(dateString);
        SimpleDateFormat outFormatter = new SimpleDateFormat("yyyy-m-d");
        return outFormatter.format(date);
    }


    public static void main(String args[]){
        try {

            XMLParser showXML = new XMLParser();
            ArrayList<HashMap<String,String>> queries = showXML.readXML ("InputFiles\\Libdata.xml");
            for(HashMap<String,String> query:queries){
                System.out.println(query);
            }
        }catch( Exception e ) {
            e.printStackTrace();

        }//end catch

    }//end main

}//end class
