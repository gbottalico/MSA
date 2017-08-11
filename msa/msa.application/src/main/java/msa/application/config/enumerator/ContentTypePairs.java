package msa.application.config.enumerator;

import org.springframework.http.MediaType;

import javax.mail.internet.ContentType;
import java.util.stream.Stream;

/**
 * Created by simon.calabrese on 11/08/2017.
 */
public enum ContentTypePairs {
    PDF("PDF","applciation/pdf"),
    DOCX("DOCX","applciation/octet-stream"),
    ZIP("ZIP","applciation/zip"),
    RAR("RAR","application/x-rar-compressed"),
    SEVENZIP("7Z","application/x-7z-compressed"),
    JPEG("JPEG","image/jpeg"),
    JPG("JPG","image/jpeg"),
    PNG("PNG","image/png"),
    XLS("XLS","application/vnd.ms-excel"),
    XLSX("XLSX","application/vnd.ms-excel");
    private String extension;
    private String contentType;

    ContentTypePairs(String extension, String contentType) {
        this.extension = extension;
        this.contentType = contentType;
    }

    public String getExtension() {
        return extension;
    }

    public String getContentType() {
        return contentType;
    }

    public static String getContentTypeByExtension(String extension) {
       return Stream.of(values())
               .filter(e -> e.getExtension().equalsIgnoreCase(extension))
               .findFirst()
               .map(ContentTypePairs::getContentType)
               .orElse(DOCX.getContentType());
    }
}
