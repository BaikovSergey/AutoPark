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

public class PhotoUpload extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
                    String sellOrderId = req.getParameter("orderId");
                    String photoName = req.getParameter("brand") + req.getParameter("model");
                    String extension = item.getName().substring(item.getName().lastIndexOf("."));
                    SellOrder sellOrder =
                            AutoPark.instOf().findSellOrderById(Integer.parseInt(sellOrderId));
                    if (sellOrder != null) {
                        File file =
                                new File(folder + File.separator + sellOrder.getBrand() + sellOrder.getModel());
                        file.delete();
                        photo.setName(sellOrderId + extension);
                        AutoPark.instOf().saveCandidatePhoto(photo, sellOrderId);
                    } else {
                        savePhoto(sellOrderId, extension);
                    }
                    File file = new File(folder + File.separator + candidateId + extension);
                    try (FileOutputStream out = new FileOutputStream(file)) {
                        out.write(item.getInputStream().readAllBytes());
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/candidates.do");
    }

    private void savePhoto(String candidateId, String extension) {
        CandidatePhoto photo = new CandidatePhoto(candidateId + extension);
        int id = Integer.parseInt(candidateId);
        photo.setCandidateId(id);
        PsqlStore.instOf().saveCandidatePhoto(photo, candidateId);
        Candidate candidate = PsqlStore.instOf().findCandidateById(id);
        candidate.setPhotoId(id);
        PsqlStore.instOf().saveCandidate(candidate);
    }
}
