package com.alex.server;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class FileUploadServlet extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        try{
            FileItemIterator iter = upload.getItemIterator(request);

            while (iter.hasNext()) {
                FileItemStream item = iter.next();

                String name = item.getFieldName();
                InputStream stream = item.openStream();

                // Process the input stream
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                int len;
                byte[] buffer = new byte[8192];
                while ((len = stream.read(buffer, 0, buffer.length)) != -1) {
                    out.write(buffer, 0, len);
                }
                response.setContentType("text/html; charset=utf-8");
                PrintWriter responseWriter = response.getWriter();
                String strOut = new String(buffer, StandardCharsets.UTF_8);
                responseWriter.println(strOut);
                responseWriter.flush();
                responseWriter.close();
            }
        }

        catch (Exception ignored) {

        }
    }
}