package pl.project.shop.admin.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.project.shop.admin.product.service.UploadedFilesNameUtils;

import static org.junit.jupiter.api.Assertions.*;

class UploadedFilesNameUtilsTest {

    @ParameterizedTest
    @CsvSource({
            "test test.png, test-test.png",
            "test -a.png, test-a.png",
            "test-a.png, test-a.png",
            "abcdęfĄgĆ.jpg, abcdefagc.jpg",
            "produkt produkt produkt.jpg, produkt-produkt-produkt.jpg",
            "produkt___1.jpeg, produkt-1.jpeg"
    })
    void shouldSlugifyFileName(String input, String output) {
        String fileName = UploadedFilesNameUtils.slugifyFileName(input);
        assertEquals(fileName, output);
    }
}