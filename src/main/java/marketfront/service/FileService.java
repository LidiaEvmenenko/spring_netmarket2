package marketfront.service;


import marketfront.entity.Product;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
//@RequiredArgsConstructor
public class FileService {
    private final String baseDir;
    private final ProductService productService;

    public FileService(@Value("${geek.storage.path}") String baseDir, ProductService productService) throws IOException {
        this.baseDir = baseDir;
        this.productService = productService;
        Path dir = Path.of(baseDir);
        if (!Files.exists(dir)){
            Files.createDirectory(dir);
        }
    }
    public void save(String data, String fileName) throws IOException {
           Files.write(getFilePath(fileName), data.getBytes(StandardCharsets.UTF_8));
    }
    private Path getFilePath(String fileName){
        return Path.of(baseDir).resolve(fileName);
    }

    public byte[] getFileData(String fileName) throws IOException {
        return Files.readAllBytes(getFilePath(fileName));
    }

    public String[] openBook(){

        List<Product> products = productService.getProducts();
    //    List<ProductDto> productDto = productService.getProductDtoWithStock(products);
        Workbook book = new HSSFWorkbook();
        Font font = book.createFont();
        CellStyle cellStyleTitle = book.createCellStyle();
        cellStyleTitle.setAlignment(HorizontalAlignment.CENTER);
        cellStyleTitle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyleTitle.setBorderBottom(BorderStyle.THIN);
        cellStyleTitle.setBorderLeft(BorderStyle.THIN);
        cellStyleTitle.setBorderRight(BorderStyle.THIN);
        cellStyleTitle.setBorderTop(BorderStyle.THIN);
        CellStyle cellStyleRow = book.createCellStyle();
        cellStyleRow.setVerticalAlignment(VerticalAlignment.CENTER);
        font.setFontHeightInPoints((short)32);
     //   font.setFontName("Courier New");
        font.setFontName("Times New Roman");
        //  font.setItalic(true);
        //  font.setStrikeout(true);
// цвет шрифта
        //  font.setColor(new Color);
        try {
            FileOutputStream fileOut  = new FileOutputStream(String.valueOf(getFilePath("book.xls")));
            //   File file = new File(path);
            Sheet sheet = book.createSheet("Лист 1");
//            sheet.setColumnWidth(0,1000);
//            sheet.setColumnWidth(1,2800);
//            sheet.setColumnWidth(2,1300);
//            sheet.setColumnWidth(3,1300);
            sheet.autoSizeColumn(0, true);
            sheet.autoSizeColumn(1, true);
            sheet.autoSizeColumn(2, true);
            sheet.autoSizeColumn(3, true);
            Row row = sheet.createRow(0);
            //   Cell cell = row.createCell(0, CellType.STRING);
            row = sheet.createRow(1);
            row.setHeightInPoints(60.0f);

            Cell cell = row.createCell(0, CellType.STRING);
            cell.setCellStyle(cellStyleTitle);
            cell.setCellValue("№№");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellStyle(cellStyleTitle);
            cell.setCellValue("  Название продукта  ");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellStyle(cellStyleTitle);
            cell.setCellValue("  Цена  ");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellStyle(cellStyleTitle);
            cell.setCellValue("  Наличие на складе  ");

            for (int i=0; i< products.size(); i++){
                row = sheet.createRow(i+2);
                row.setHeightInPoints(20.0f);
                cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellStyle(cellStyleRow);
                cell.setCellValue(i + 1);
                cell = row.createCell(1, CellType.STRING);
                cell.setCellStyle(cellStyleRow);
                cell.setCellValue(products.get(i).getTitle());
                cell = row.createCell(2, CellType.NUMERIC);
                cell.setCellStyle(cellStyleRow);
                cell.setCellValue(products.get(i).getPrice());
                cell = row.createCell(3, CellType.NUMERIC);
                cell.setCellStyle(cellStyleRow);
                cell.setCellValue(products.get(i).getWeight());
            }
            book.write(fileOut);
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] p = new String[]{"localhost:8189","app","api","v1","files","Список продуктов.xls"};
    //    String p = "Список продуктов.xls";
        return  p;
    }
}

















