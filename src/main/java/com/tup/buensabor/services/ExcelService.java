package com.tup.buensabor.services;

import com.tup.buensabor.dtos.ranking.ArticuloManufacturadoRankingDto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExcelService {

    public Workbook createExcelFileRankingProductos(List<ArticuloManufacturadoRankingDto> articulos) {
        Workbook workbook = new XSSFWorkbook();
        CreationHelper createHelper = workbook.getCreationHelper();

        Sheet sheet = workbook.createSheet("MySheet");

        CellStyle headerCellStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);

        Row headerRow = sheet.createRow(0);

        String[] columns = {"Denominacion", "Cantidad Vendida", "Costo", "Precio Venta", "Costo Total", "Venta Total", "Utilidad Total"};
        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        int rowNum = 1;
        CellStyle dataCellStyle = workbook.createCellStyle();
        dataCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("\"$\"#,##0.00_-"));

        for (ArticuloManufacturadoRankingDto articulo : articulos) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(articulo.getDenominacion());
            row.createCell(1).setCellValue(articulo.getCantidadVendida());

            Cell costoCell = row.createCell(2);
            costoCell.setCellValue(articulo.getCosto().doubleValue());
            costoCell.setCellStyle(dataCellStyle);

            Cell precioVentaCell = row.createCell(3);
            precioVentaCell.setCellValue(articulo.getPrecioVenta().doubleValue());
            precioVentaCell.setCellStyle(dataCellStyle);

            Cell costoTotalCell = row.createCell(4);
            costoTotalCell.setCellValue(articulo.getCostoTotal().doubleValue());
            costoTotalCell.setCellStyle(dataCellStyle);

            Cell ventaTotalCell = row.createCell(5);
            ventaTotalCell.setCellValue(articulo.getVentaTotal().doubleValue());
            ventaTotalCell.setCellStyle(dataCellStyle);

            Cell utilidadTotalCell = row.createCell(6);
            utilidadTotalCell.setCellValue(articulo.getUtilidadTotal().doubleValue());
            utilidadTotalCell.setCellStyle(dataCellStyle);
        }

        for(int i = 0; i < columns.length; i++) {
            if (i >= 2) { // "Costo", "Precio Venta", "Costo Total", "Venta Total", "Utilidad Total"
                sheet.setColumnWidth(i, 5000);
            } else {
                sheet.autoSizeColumn(i);
            }
        }

        return workbook;
    }

}
