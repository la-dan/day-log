package cn.vlinker.logclient.decorate;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tooltip;
import javafx.util.Callback;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class DateDecorate implements Callback<DatePicker, DateCell> {

    private List<Map<String, Object>> dates;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public DateDecorate(List dates) {
        this.dates = dates;
    }

    @Override
    public DateCell call(DatePicker param) {
        DateCell dateCell = new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                dates.forEach(i -> {
                    LocalDate day = LocalDate.parse(i.get("day").toString(), formatter);
                    String work = (String) i.get("work");
                    if (item.isEqual(day)) {
                        setItem(day);
                        setTooltip(new Tooltip(work));
                        setStyle("-fx-background-color: #4bff53;");
                    }
                });
            }
        };
        return dateCell;
    }
}
