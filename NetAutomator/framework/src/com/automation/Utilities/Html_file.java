package com.automation.Utilities;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import com.automation.Utilities.Html_Merger;

public class Html_file {
	
		
		public static ArrayList<String> file_path = new ArrayList<String>();
		
		public static PrintStream pagePs = null;
		static FileOutputStream pageFos = null;
	    static FileOutputStream pageParent = null;
	    static String url = "https://www.cavisson.com/"   ;
		
	    public static void merger() throws FileNotFoundException
		{
	    	file_path=Html_Merger.links();
			    int size=file_path.size();
			    System.out.println(size);
			    
			   // pageFos = new FileOutputStream("pagesList.html");
			    pageParent = new FileOutputStream(Html_Merger.outFileName);
			    pagePs = new PrintStream(pageParent);
			    pagePs.print("<html lang=\"en\" dir=\"ltr\" \n>");
			    pagePs.print("<head> \n <meta charset=\"utf-8\" \n>");
			    pagePs.print("<title>ArchChat Test Automation Report </title> \n");
			    pagePs.print("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"> \n");
			    pagePs.print("<style>\r\n" + 
			    		"    .footer {\r\n" + 
			    		"       position: fixed;\r\n" + 
			    		"       left: 0;\r\n" + 
			    		"       bottom: 0;\r\n" + 
			    		"       width: 100%;\r\n" + 
			    		"       background-color: #f1f1f1;\r\n" + 
			    		"       color: #080808;\r\n" + 
			    		"       text-align: center;\r\n" + 
			    		"\r\n" + 
			    		"\r\n" + 
			    		"    }\r\n" + 
			    		"    </style> \n");
			    
			    pagePs.print("<style>\r\n" + 
			    		"* {box-sizing: border-box;}\r\n" + 
			    		"\r\n" + 
			    		"body {\r\n" + 
			    		"  margin: 0;\r\n" + 
			    		"  font-family: Arial, Helvetica, sans-serif;\r\n" + 
			    		"}\r\n" + 
			    		"\r\n" + 
			    		".header {\r\n" + 
			    		"  overflow: hidden;\r\n" + 
			    		"  background-color: #f1f1f1;\r\n" + 
			    		"  padding: 20px 10px;\r\n" + 
			    		"}\r\n" + 
			    		"\r\n" + 
			    		".header a {\r\n" + 
			    		"  float: left;\r\n" + 
			    		"  color: black;\r\n" + 
			    		"  text-align: center;\r\n" + 
			    		"  padding: 12px;\r\n" + 
			    		"  text-decoration: none;\r\n" + 
			    		"  font-size: 18px;\r\n" + 
			    		"  line-height: 25px;\r\n" + 
			    		"  border-radius: 4px;\r\n" + 
			    		"}\r\n" + 
			    		"\r\n" + 
			    		".header a.logo {\r\n" + 
			    		"  font-size: 25px;\r\n" + 
			    		"  font-weight: bold;\r\n" + 
			    		"}\r\n" + 
			    		"\r\n" + 
			    		".header a:hover {\r\n" + 
			    		"  background-color: #ddd;\r\n" + 
			    		"  color: black;\r\n" + 
			    		"}\r\n" + 
			    		"\r\n" + 
			    		".header a.active {\r\n" + 
			    		"  background-color: dodgerblue;\r\n" + 
			    		"  color: white;\r\n" + 
			    		"}\r\n" + 
			    		"\r\n" + 
			    		".header-right {\r\n" + 
			    		"  float: right;\r\n" + 
			    		"}\r\n" + 
			    		".table.tableWidth {\r\n" + 
			    		"width: 30%;\r\n" + 
			    		"\r\n" + 
			    		"@media screen and (max-width: 500px) {\r\n" + 
			    		"  .header a {\r\n" + 
			    		"    float: none;\r\n" + 
			    		"    display: block;\r\n" + 
			    		"    text-align: left;\r\n" + 
			    		"  }\r\n" + 
			    		"\r\n" + 
			    		"  .header-right {\r\n" + 
			    		"    float: none;\r\n" + 
			    		"  }\r\n" + 
			    		"}\r\n" + 
			    		"</style>\r\n" + 
			    		"  </head> \n");
			    
			    pagePs.print("<body>\r\n" + 
			    		"    <div class=\"footer\">\r\n" + 
			    		"      \r\n" + 
			    		"      <h1>CAVISSON</h1>\r\n" + 
			    		"    </div>\r\n" + 
			    		"\r\n" + 
			    		"<div class=\"header \">\r\n" + 		    	
			    		"<a href=" + url + " class=\"logo\">Cavisson</a> \n"+
			    		"  <div class=\"header-right\">\r\n" + 
			    		"    <h5 class=\"logo\"> ArchChat</h5>\r\n" + 
			    		"\r\n" + 
			    		"  </div>\r\n" + 
			    		"</div> \n");
			    
			    pagePs.print("<table class=\"table tableWidth john table-bordered killmarginpadding table-striped \"> \n");
			    
			    
			    pagePs.print("<tr>\r\n" + 
			    		"  <thead>\r\n" + 
			    		"    <th>Functionality</th>\r\n" + 
			    		"  <th>Link to Report</th>\r\n" + 
			    		"\r\n" + 
			    		"      </thead>\r\n" + 
			    		"</tr>" );
			    pagePs.print("<h1>Page Detail Report</h1>");
			    for(int i =0;i <size;i++)
			    {	
			    	try
			    	{
			    		
			    	String name_of_file=file_path.get(i).substring(file_path.get(i).lastIndexOf("\\") +1  , file_path.get(i).lastIndexOf(".html")) ;
			    	pagePs.print("<tr>\r\n " +
			    			 " <td><p>" + name_of_file + " -> functionality report</p></td>\r\n" +
			    			 " <td><a href="+file_path.get(i)+">" + name_of_file + "</a></td>\r\n" +
			    			 "</tr>" );
			    	}
			    	
			    	catch(Exception e)
			    	{
			    		System.out.println(e.getMessage());
			    	}
			    }
			    
			    
			    pagePs.print("<script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\"\r\n" + 
			    		"    integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\"\r\n" + 
			    		"     crossorigin=\"anonymous\"></script> \n");
			    
			    pagePs.print("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\"\r\n" + 
			    		"integrity=\"sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1\"\r\n" + 
			    		"crossorigin=\"anonymous\"></script> \n");
			    
			    pagePs.print("<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\"\r\n" + 
			    		"integrity=\"sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM\"\r\n" + 
			    		"crossorigin=\"anonymous\"></script> \n");
			    
			    pagePs.print("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\" \n>");
			    
			    pagePs.print("<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\" integrity=\"sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM\" crossorigin=\"anonymous\"></script> \n");
			    
			    pagePs.print("</body></html>" );
			    
			    
			    System.out.println("html file created");
			    
			    
			    
		}
	
	}
