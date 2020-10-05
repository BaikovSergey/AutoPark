package ru.job4j.presentation;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.job4j.application.AutoPark;
import ru.job4j.domain.CarPhoto;
import ru.job4j.domain.SellOrder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PhotoUpload extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(PhotoUpload.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("orderId");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> items = upload.parseRequest(req);
            File folder = new File("autoParkCarsImages");
            if (!folder.exists()) {
                folder.mkdir();
            }
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    int sellOrderId = Integer.parseInt(id);
                    String extension = item.getName().substring(item.getName().lastIndexOf("."));
                    SellOrder sellOrder = AutoPark.instOf().findSellOrderById(sellOrderId);
                    String photoName = sellOrderId + extension;
                    CarPhoto photo = new CarPhoto(photoName, sellOrderId);
                    sellOrder.setCarPhoto(AutoPark.instOf().createCarPhoto(photo).getName());
                    AutoPark.instOf().updateSellOrder(sellOrder);
                    File file = new File(folder + File.separator + photoName);
                    try (FileOutputStream out = new FileOutputStream(file)) {
                        out.write(item.getInputStream().readAllBytes());
                    }
                }
            }
        } catch (FileUploadException e) {
            LOGGER.log(Level.SEVERE, "File doesn't load", e);
        }
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

}
