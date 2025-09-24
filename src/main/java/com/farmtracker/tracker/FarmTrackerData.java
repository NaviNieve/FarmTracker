package com.farmtracker.tracker;

import com.farmtracker.FarmTrackerConfig;
import com.farmtracker.FarmTrackerPlugin;
import lombok.Getter;
import lombok.Setter;
import net.runelite.client.game.ItemManager;

import java.text.NumberFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Objects;

public class FarmTrackerData {
    //String[] Columns = {"Run #", "Item", "Quantity", "Price", "Total", "Diseased", "Dead", "Time", "Date", "Used", "Seed #"};

    @Getter
    public int TabType;
    @Getter @Setter
    public int RunNumber;
    @Getter @Setter
    public String ItemName;
    @Getter @Setter
    public int ItemID;
    @Getter
    public int Quantity;
    @Getter
    public int Price = -1;
    @Getter
    public int Total;
    @Getter @Setter
    public int Diseased = 0;
    @Getter @Setter
    public int Dead = 0;
    @Getter @Setter
    public String Time;
    @Getter @Setter
    public String Date;
    @Getter @Setter
    public int SeedsUsed = 0;
    @Getter @Setter
    public int GESeedsNumber = -1;

    private final FarmTrackerPlugin plugin;
    private final ItemManager itemManager;
    private final FarmTrackerConfig config;

    /**
     * Used for New Data
     */
    FarmTrackerData(int TabType, String ItemName, int ItemID, int Quantity, final FarmTrackerPlugin plugin, ItemManager itemManager, FarmTrackerConfig config) {
        this.TabType = TabType;
        this.itemManager = itemManager;
        this.plugin = plugin;
        this.config = config;
        this.ItemName = ItemName;
        this.ItemID = ItemID;
        this.Quantity = Quantity;

        plugin.clientThread.invokeLater(() -> {
            this.Price = itemManager.getItemPrice(ItemID);
            this.Total = this.Quantity * this.Price;
        });
        getTimeAndDate();

        this.Total = this.Quantity * this.Price;
    }
    /**
     * Used For Loading
     */
    FarmTrackerData(int RunNumber, String ItemName, int ItemID, int Quantity, int Price, int Diseased, int Dead, String Time, String Date, int SeedsUsed, int GESeedsNumber, final FarmTrackerPlugin plugin, ItemManager itemManager, FarmTrackerConfig config) {
        this.itemManager = itemManager;
        this.plugin = plugin;
        this.config = config;
        this.RunNumber = RunNumber;
        this.ItemName = ItemName;
        this.ItemID = ItemID;
        this.Quantity = Quantity;
        this.Price = Price;
        this.Diseased = Diseased;
        this.Dead = Dead;
        this.Time = Time;
        this.Date = Date;
        this.SeedsUsed = SeedsUsed;
        this.GESeedsNumber = GESeedsNumber;

        this.Total = this.Quantity * this.Price;
    }

    private void getTimeAndDate() {
        boolean use12HourFormat = config.useTwelveFormat();
        LocalDateTime time = LocalDateTime.now();

        if(use12HourFormat) {
            DateTimeFormatter DATETIME_FORMATTER_12H = DateTimeFormatter.ofPattern("h:mm a");
            String format = DATETIME_FORMATTER_12H.format(time);
            String[] split = format.split(":");
            String[] split2 = split[1].split(" ");
            String Hour = split[0];
            String Minute = split2[0];

            try {
                int toIntHour = Integer.parseInt(Hour);
                int toIntMinute = Integer.parseInt(Minute);
                String timeTarget = split2[1];

                int toMinusHour = 0;
                int minTarget;
                int hourTarget = toIntHour;
                int dayTarget = time.getDayOfMonth();
                int monthTarget = time.getMonthValue();
                int yearTarget = time.getYear();

                if(toIntMinute < 19) {
                    toMinusHour = 1;
                    minTarget = 59;
                } else if(toIntMinute < 39) {
                    minTarget = 19;
                } else if (toIntMinute < 59) {
                    minTarget = 39;
                } else {
                    minTarget = 59;
                }
                if(toMinusHour > 0) {
                    if(toIntHour == 1) {
                        hourTarget = 12;

                        if(Objects.equals(timeTarget, "PM")) {
                            timeTarget = "AM";
                        } else {
                            timeTarget = "PM";
                            if(time.getDayOfMonth() == 1) {
                                if(time.getMonthValue() == 1) {
                                    monthTarget = 12;
                                    yearTarget = time.getYear()-1;
                                } else {
                                    monthTarget = time.getMonthValue()-1;
                                }
                                dayTarget = getMaxDaysInMonth(monthTarget, yearTarget); // End of Month
                            } else {
                                dayTarget = time.getDayOfMonth()-1;
                            }
                        }
                    } else {
                        hourTarget = toIntHour-1;
                    }
                }
                Time = hourTarget + ":" + minTarget + " " + timeTarget;

                //Date
                int hourFormatTarget = hourTarget;
                if(Objects.equals(timeTarget, "PM")) hourFormatTarget = hourFormatTarget+12;
                LocalDateTime pastDateTime = LocalDateTime.of(yearTarget, monthTarget, dayTarget, hourFormatTarget, minTarget, time.getSecond());
                Duration duration = Duration.between(pastDateTime, time);
                LocalDateTime startTime = LocalDateTime.now().minusSeconds(duration.toSeconds());
                if (startTime.getDayOfWeek() != time.getDayOfWeek())
                {
                    Date = startTime.getMonthValue() + "/" + startTime.getDayOfMonth() + "/" + startTime.getYear();
                } else {
                    Date = time.getMonthValue() + "/" + time.getDayOfMonth() + "/" + time.getYear();
                }

            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        } else {
            DateTimeFormatter DATETIME_FORMATTER_24H = DateTimeFormatter.ofPattern("HH:mm");
            String format = DATETIME_FORMATTER_24H.format(time);

            String[] split = format.split(":");
            String Hour = split[0];
            String Minute = split[1];

            try {
                int toIntHour = Integer.parseInt(Hour);
                int toIntMinute = Integer.parseInt(Minute);

                int toMinusHour = 0;
                int minTarget;
                int hourTarget = time.getHour();
                int dayTarget = time.getDayOfMonth();
                int monthTarget = time.getMonthValue();
                int yearTarget = time.getYear();

                if(toIntMinute < 19) {
                    toMinusHour = 1;
                    minTarget = 59;
                } else if(toIntMinute < 39) {
                    minTarget = 19;
                } else if (toIntMinute < 59) {
                    minTarget = 39;
                } else {
                    minTarget = 59;
                }
                if(toMinusHour > 0) {
                    if(toIntHour == 0) {
                        hourTarget = 23;
                        if(time.getDayOfMonth() == 1) {
                            if(time.getMonthValue() == 1) {
                                monthTarget = 12;
                                yearTarget = time.getYear()-1;
                            } else {
                                monthTarget = time.getMonthValue()-1;
                            }
                            dayTarget = getMaxDaysInMonth(monthTarget, yearTarget); // End of Month
                        } else {
                            dayTarget = time.getDayOfMonth()-1;
                        }
                    } else {
                        hourTarget = toIntHour-1;
                    }
                }
                Time = hourTarget + ":" + minTarget;

                //Date
                LocalDateTime pastDateTime = LocalDateTime.of(yearTarget, monthTarget, dayTarget, hourTarget, minTarget, time.getSecond());
                Duration duration = Duration.between(pastDateTime, time);
                LocalDateTime startTime = LocalDateTime.now().minusSeconds(duration.toSeconds());
                if (startTime.getDayOfWeek() != time.getDayOfWeek())
                {
                    Date = startTime.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.getDefault());
                } else {
                    Date = time.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.getDefault());
                }

                } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private int getMaxDaysInMonth(int month, int year) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12: {
                return 31;
            }
            case 2: {
                LocalDate date = LocalDate.of(year, month, 1); // Create a LocalDate object for the given year
                boolean isLeap = date.isLeapYear();
                if(isLeap) return 29;
                else return 28;
            }
            case 4:
            case 6:
            case 9:
            case 11: {
                return 30;
            }
            default: {
                return 0;
            }
        }
    }
    private String getFormatInt(int number) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        return nf.format(number);
    }
    private int getUnformatInt(String numberStr) {
        String numberReplace = numberStr.replaceAll(",", "");
        return Integer.parseInt(numberReplace);
    }

    //Public
    public void addDiseased() {
        Diseased++;
    }
    public void addDead() {
        Dead++;
    }
    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
        this.Total = this.Quantity * this.Price;
    }
    public void addQuantity() {
        Quantity++;
        this.Total = this.Quantity * this.Price;
    }
    public void setPrice(int Price) {
        this.Price = Price;
        this.Total = this.Quantity * this.Price;
    }

    //ToString
    @Override
    public String toString() {
        return super.toString();
    }
}
