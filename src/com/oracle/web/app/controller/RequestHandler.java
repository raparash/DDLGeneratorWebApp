/**
 * 
 */
package com.oracle.web.app.controller;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.oracle.db.helper.DDLGenerator;
import com.oracle.web.app.util.EmailUtil;
import com.oracle.web.fmwk.ModelView;
import com.oracle.web.fmwk.RequestMapping;
import com.oracle.web.fmwk.ViewType;
import com.oracle.web.servlet.WebController;
import com.oracle.web.util.PropertyManager;
import com.oracle.web.util.StringUtil;

/**
 * @author raparash
 *
 */

public class RequestHandler implements WebController {
	private static final Logger LOGGER = Logger.getLogger(RequestHandler.class.getName());

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/upload_file.wss")
	public ModelView uploadFile(HttpServletRequest request, HttpServletResponse response) {
		ModelView modelView = null;
		try {
			modelView = new ModelView(ViewType.AJAX_VIEW);
			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			String email = null;
			String fileName = null;
			InputStream inputStream = null;
			for (FileItem item : items) {
				String fieldName = item.getFieldName();
				if (item.isFormField()) {
					if ("email".equalsIgnoreCase(fieldName)) {
						email = item.getString();
					}
				} else {
					if ("uploadFile".equalsIgnoreCase(fieldName)) {
						fileName = FilenameUtils.getName(item.getName());
						inputStream = item.getInputStream();
					}
				}
			}
			String defaultDestination = PropertyManager.getValue("upload.properties", "upload.dir");
			DDLGenerator.writeContentToSQLDump(inputStream, defaultDestination, fileName);
			modelView.setBody("File Upload Successfully. Email sent="+sendMail(email, defaultDestination, fileName));
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Exception|RequestHandler|uploadFile()|", e);
		}
		return modelView;
	}
	
	private boolean sendMail(String email,String destination,String fileName){
		List<String> to=new ArrayList<String>();
		String body=null;
		String checkFile=destination+File.separatorChar+fileName+"_checkDBObjects.sql";
		String dumpFile=destination+File.separatorChar+fileName+"_sqldump.sql";
		List<String> files=new ArrayList<String>();
		List<String> cc=new ArrayList<String>();
		boolean isSuccessful=false;
		try{
			if(email!=null){
				for(String eml:email.split(",")){
					to.add(eml);
				}
				files.add(dumpFile);
				files.add(checkFile);
				for(String cceml:StringUtil.safeTrim(PropertyManager.getValue("email.properties", "mail.default.cc")).split(",")){
					cc.add(cceml);	
				}
				body=StringUtil.safeTrim(PropertyManager.getValue("email.properties", "mail.default.body"));
				isSuccessful=EmailUtil.sendEmail(to, cc, null, "ddl_generator@oracle.com", "SQL dumps for "+fileName, body, files);
			}
		}catch(Exception e){
			LOGGER.log(Level.WARNING, "Exception|RequestHandler|uploadFile()|", e);
		}
		return isSuccessful;
	}

}
