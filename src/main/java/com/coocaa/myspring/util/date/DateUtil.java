package com.coocaa.myspring.util.date;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * 时间类型操作类
 * @author zyb
 *         since 2011-4-11
 */
@SuppressWarnings("unused")
public class DateUtil {
    public static String DEFAULT_PATTERN_YYMMMMDD = "yyyy-MM-dd";
    public static String DEFAULT_PATTERN_YYMMMMDDHHmmss = "yyyy年MM月dd日";
    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /**
     * @param strDate
     * @return
     */
    public static Timestamp string2TimeStamp(String strDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date());
        Timestamp ts = Timestamp.valueOf(time);
        return ts;
    }

    /**
     * @param strDate
     * @return
     */
    public static Timestamp str2TimeStamp(String strDate) {
        Timestamp ts = Timestamp.valueOf(strDate);
        return ts;
    }

    /**
     * 根据传入的模式参数返回当天的日期
     * @param pattern 传入的模式
     * @return 按传入的模式返回一个字符串
     */
    public static String getToday(String pattern) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 比较两个日期大小
     * @param date1    日期字符串
     * @param pattern1 日期格式
     * @param date2    日期字符串
     * @param pattern2 日期格式
     * @return boolean 若是date1比date2小则返回true
     */
    public static boolean compareMinDate(String date1, String pattern1,
                                         String date2, String pattern2)
            throws ParseException {
        Date d1 = convertToCalendar(date1, pattern1).getTime();
        Date d2 = convertToCalendar(date2, pattern2).getTime();
        return d1.before(d2);
    }

    /**
     * 比较两个日期大小
     * @param date1 Date
     * @param date2 Date
     * @return boolean 若是date1比date2小则返回true
     */
    public static boolean compareMinDate(Date date1, Date date2) {
        try {
            return DateUtil.compareMinDate(DateUtil.formatDate(date1, "yyyy-MM-dd HH:mm:ss"),
                    "yyyy-MM-dd HH:mm:ss",
                    DateUtil.formatDate(date2, "yyyy-MM-dd HH:mm:ss"),
                    "yyyy-MM-dd HH:mm:ss");
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * 比较两个日期大小
     * @param date1 Date
     * @param date2 Date
     * @return boolean 若是date1比date2小则返回true
     */
    public static boolean compareMinDateForDay(Date date1, Date date2) {
        try {
            return DateUtil.compareMinDate(DateUtil.formatDate(date1, "yyyy-MM-dd"),
                    "yyyy-MM-dd",
                    DateUtil.formatDate(date2, "yyyy-MM-dd"),
                    "yyyy-MM-dd");
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * 根据传入的日期字符串以及格式，产生一个Calendar对象
     * @param date    日期字符串
     * @param pattern 日期格式
     * @return Calendar
     * @throws ParseException 当格式与日期字符串不匹配时抛出该异常
     */
    public static Calendar convertToCalendar(String date, String pattern)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date d = sdf.parse(date);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(d);
        return calendar;
    }

    /**
     * 用途：以指定的格式格式化日期字符串
     * @param pattern     字符串的格式
     * @param currentDate 被格式化日期
     * @return String 已格式化的日期字符串
     * @throws NullPointerException 如果参数为空
     */
    public static String formatDate(Calendar currentDate, String pattern) {

        Date date = currentDate.getTime();
        return formatDate(date, pattern);
    }

    /**
     * 用途：以指定的格式格式化日期字符串
     * @param pattern     字符串的格式
     * @param currentDate 被格式化日期
     * @return String 已格式化的日期字符串
     * @throws NullPointerException 如果参数为空
     */
    public static String formatDate(Date currentDate, String pattern) {

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(currentDate);
    }

    /**
     * 用途：以指定的格式格式化日期字符串
     * @param currentDate 被格式化日期字符串 必须为yyyymmdd
     * @param pattern     字符串的格式
     * @return String 已格式化的日期字符串
     * @throws NullPointerException     如果参数为空
     * @throws ParseException 若被格式化日期字符串不是yyyymmdd形式时抛出
     */
    public static String formatDate(String currentDate, String pattern)
            throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = sdf.parse(currentDate);
        sdf.applyPattern(pattern);
        return sdf.format(date);
    }

    /**
     * 用途：以指定的格式格式化日期字符串
     * @param strDate  被格式化日期字符串 必须为yyyymmdd
     * @param formator 格式字符串
     * @return String 已格式化的日期字符串
     * @throws NullPointerException     如果参数为空
     * @throws ParseException 若被格式化日期字符串不是yyyymmdd形式时抛出
     */
    public static Calendar strToDate(String strDate, String formator) {


        Calendar date = Calendar.getInstance();
        date.setTime(java.sql.Date.valueOf(strDate));
        return date;
    }

    /**
     * 按分钟计算间隔
     */
    public static boolean isInTimeMinute(Date startdate, Date enddate, int minute) {
        long start = startdate.getTime();
        long end = enddate.getTime();
        long pass = end - start;
        long passminute = pass / (1000 * 60);
        if (passminute > minute) return false;
        else return true;

    }

    /**
     * 判断当前时间是否在参数时间内（当开始时间大于结束时间表示时间段的划分从begin到第二天的end时刻）
     * 例如当前时间在12：00 传入参数为（12,12,0,1）返回true
     * 例如当前时间在12：00 传入参数为（12,12,1,0）返回true
     * @param beginHour int 开始的小时值
     * @param endHour   int   结束的小时值
     * @param beginMinu int 开始的分钟值
     * @param endMinu   int   结束的分钟值
     * @return boolean
     */
    @SuppressWarnings("deprecation")
    public static boolean isInTime(int beginHour, int endHour,
                                   int beginMinu,
                                   int endMinu) {
        Date date1 = new Date();
        Date date2 = new Date();
        Date nowDate = new Date();
        date1.setHours(beginHour);
        date2.setHours(endHour);
        date1.setMinutes(beginMinu);
        date2.setMinutes(endMinu);
        if (date1 == date2) {
            return false;
        }
        //yyyy-MM-dd HH:mm:ss
        if (
                DateUtil.compare(date2, date1)) {
            if (!DateUtil.compare(nowDate, date1)
                    || DateUtil.compare(nowDate, date2)) {
                return true;
            }
        } else {
            if (
                    !DateUtil.compare(nowDate, date1) &&
                            DateUtil.compare(nowDate, date2)
                    ) {
                return true;
            }
        }
        return false;
    }

    /**
     * 开始时间小于结束时间返回true，否则返回false
     * @param beginDate Date
     * @param endDate   Date
     * @return boolean
     */
    private static boolean compare(Date beginDate, Date endDate) {
        try {

            return DateUtil.compareMinDate(DateUtil.formatDate(beginDate,
                    "yyyy-MM-dd HH:mm:ss"),
                    "yyyy-MM-dd HH:mm:ss",
                    DateUtil.formatDate(endDate,
                            "yyyy-MM-dd HH:mm:ss"),
                    "yyyy-MM-dd HH:mm:ss");

        } catch (Exception ex) {
//            log.error ( "时间格式转换错误" + ex ) ;
            return false;
        }
    }

    /**
     * 将指定格式的时间String转为Date类型
     * @param dateStr String 待转换的时间String
     * @param pattern String 转换的类型
     * @return Date
     */
    public static Date convertStringToDate(String dateStr, String pattern) {
        if (dateStr == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    public static String convertDateToString(Date date)

    {
        if (date == null) {
            return "";
        }
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }


    /**
     * 获取比当前日期早多少天或者晚多少天的日期 例如 前五天 －5    后五天   5
     * @param format 返回日期的格式
     * @return 格式化好的字符串
     */

    public static String DateBefAft(int days, String format) {
        //
        if (format == null || "".equals(format))
            format = "yyyy-MM-dd";
        Calendar now = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        now.add(Calendar.DAY_OF_YEAR, -days);
        return formatter.format(now.getTime());
    }

    /**
     * 获取比当前日期早多少天或者晚多少天的日期 例如 前五天 －5    后五天   5
     * @param format 返回日期的格式
     * @return 日期
     */

    public static Date DateBefAft_returnDate(int days, String format) throws ParseException {
        //
        if (format == null || "".equals(format))
            format = "yyyy-MM-dd";
        Calendar now = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        now.add(Calendar.DAY_OF_YEAR, -days);
        return convertStringToDate(formatter.format(now.getTime()), "yyyy-MM-dd");
    }

    /**
     * 格式化 Date 类型的日期
     * @param date   传入日期
     * @param format 设定日期的显示格式 默认 2006-12-25
     * @return 格式化后的日期
     */
    public static String DatetoString(Date date, String format) {
        if (format == null || "".equals(format))
            format = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    public static void shortDateToLongDate(String strDate) {

    }

    /**
     * 获取某时间的中文星期（如：星期一、星期二），每星期的第一天是星期日
     */
    public static String getWeekCS(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String[] week = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        return week[calendar.get(Calendar.DAY_OF_WEEK) - 1];
    }

    /**
     * 获取当前时间的中文星期（如：星期一、星期二），每星期的第一天是星期日
     */
    public static String getWeekCSToday() {
        return getWeekCS(new Date());
    }

    /**
     * 用当前日期作为文件名，一般不会重名取到的值是从当前时间的字符串格式，带有微秒，建议作为记录id
     */
    public static String getTimeStamp(String strFormat) {
        Date currentTime = new Date();
        return dateToString(currentTime, strFormat);
    }

    /**
     * 用当前日期作为文件名，一般不会重名取到的值是从1970年1月1日00:00:00开始算起所经过的微秒数
     */
    public static String getFileName() {
        Calendar calendar = Calendar.getInstance();
        String filename = String.valueOf(calendar.getTimeInMillis());
        return filename;
    }

    /**
     * 获取两个日期之间所差的天数
     * @return：所差的天数(非负整数)
     */
    public static int dateNum(Date from, Date to) {
        if (null == from || null == to) return -1;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(from);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date fromDate = calendar.getTime();

        calendar.setTime(to);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date toDate = calendar.getTime();
        int diff = Math.abs((int) ((fromDate.getTime() - toDate.getTime()) / (24 * 3600 * 1000)));
        return diff;
    }

    /**
     * 获取两个日期之间所差的周数
     */
    public static int weekNum(Date from, Date to) {

        return 0;
    }

    /**
     * 获取date前或后nDay天的日期
     */
    private static Date getDate(Date date, int nDay, int type, String format) {
        long millisecond = date.getTime(); //从1970年1月1日00:00:00开始算起所经过的微秒数
        long msel = nDay * 24 * 3600 * 1000l; //获取nDay天总的毫秒数
        millisecond = millisecond + ((type > 0) ? msel : (-msel));
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millisecond);
        return calendar.getTime();
    }

    /**
     * <b>概要：</b>
     * 获取n天后的日期
     * <b>作者：</b>SUXH </br>
     * <b>日期：</b>2015-3-14 </br>
     * @param date   选取起始的日期
     * @param nDay   间隔天数
     * @param format 日期格式字符串
     * @return n天后的日期
     */
    public static Date dateAfterNDate(Date date, int nDay, String format) {
        return getDate(date, nDay, 1, format);
    }

    /**
     * 获取n天后的日期
     */
    public static Date dateAfterNDate(String strDate, int nDay, String format) {
        Date date = stringToDate(strDate, format);
        return dateAfterNDate(date, nDay, format);
    }

    /**
     * 获取n天前的日期
     */
    public static Date dateBeforeNDate(Date date, int nDay, String format) {
        return getDate(date, nDay, -1, format);
    }

    /**
     * 获取n天前的日期
     */
    public static Date dateBeforeNDate(String strDate, int nDay, String format) {
        Date date = stringToDate(strDate, format);
        return dateBeforeNDate(date, nDay, format);
    }

    /**
     * <b>概要：</b>
     * 将日期转化为字符串的形式
     * <b>作者：</b>SUXH </br>
     * <b>日期：</b>2015-3-14 </br>
     * @param date      指定日期
     * @param strFormat 日期格式字符串 如："YYYY-MM-DD"
     * @return 日期对应的字符串类型
     */
    public static String dateToString(Date date, String strFormat) {
        if (date == null) {
            return null;
        }
        if (strFormat == null) {
            strFormat = "yyyy-MM-dd";
        }
        SimpleDateFormat format = new SimpleDateFormat(strFormat);
        return format.format(date);
    }

    /**
     * 将字符串转化为Date类型。如果该字符串无法转化为Date类型的数据，则返回null。
     * @param strFormat strDate和strFormat的格式要一样。即如果strDate="20061112"，则strFormat="yyyyMMdd"
     */
    public static Date stringToDate(String strDate, String strFormat) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
            date = sdf.parse(strDate);
            if (!sdf.format(date).equals(strDate)) {
                date = null;
            }
        } catch (Exception e) {
            date = null;
        }
        return date;
    }

    /**
     * 获取n月之前或之后的日期
     */
    public static Date getDateMonth(Date date, int nMonth, int type) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int nYear = nMonth / 12;
        int month = nMonth % 12;
        calendar.add(Calendar.YEAR, nYear * type);
        Date desDate = calendar.getTime();
        calendar.add(Calendar.MONTH, month * type);
        if (type < 0) {
            while (!calendar.getTime().before(desDate)) {
                calendar.add(Calendar.YEAR, type);
            }
        } else {
            while (!calendar.getTime().after(desDate)) {
                calendar.add(Calendar.YEAR, type);
            }
        }
        return calendar.getTime();
    }

    /**
     * 获取当前时间所在的周的最后一天（周一为第一天）
     */
    public static Date getLastDateOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int index = calendar.get(Calendar.DAY_OF_WEEK);
        index = (index == 1) ? (index + 7) : index;
        date = DateUtil.dateAfterNDate(date, 8 - index, "yyyy-MM-dd");
        return date;
    }

    /**
     * 获取当前时间所在的周的第一天（周一为第一天）
     */
    public static Date getFirstDateOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int index = calendar.get(Calendar.DAY_OF_WEEK);
        index = (index == 1) ? (index + 7) : index;
        date = DateUtil.dateBeforeNDate(date, index - 2, "yyyy-MM-dd");
        return date;
    }

    /**
     * 获取date所在的月份的最后一天
     * 方法是获取下个月的第一天，然后获取前一天的日期
     */
    public static Date getLastDateOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        date = calendar.getTime();
        date = DateUtil.getDateMonth(date, 1, 1);
        date = DateUtil.dateBeforeNDate(date, 1, "yyyy-MM-dd");
        return date;
    }

    public static Date getFirstDateOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /**
     * 获取季度的最后一天
     */
    public static Date getLastDateOfSeason(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int index = calendar.get(Calendar.MONTH);
        index = index / 3;
        Date[] dates = new Date[4];
        calendar.set(calendar.get(Calendar.YEAR), 2, 31);
        dates[0] = calendar.getTime();
        calendar.set(calendar.get(Calendar.YEAR), 5, 30);
        dates[1] = calendar.getTime();
        calendar.set(calendar.get(Calendar.YEAR), 8, 30);
        dates[2] = calendar.getTime();
        calendar.set(calendar.get(Calendar.YEAR), 11, 31);
        dates[3] = calendar.getTime();
        return dates[index];
    }

    /**
     * 创建日期date
     */
    public static Date createDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }

    /**
     * <b>概要：</b>
     * 获取指定月（n）后日期
     * <b>作者：</b>SUXH </br>
     * <b>日期：</b>2015-3-31 </br>
     * @param date 指定日期
     * @param n    n个月
     * @return n个月后的日期
     */
    public static Date getDateAfterMonths(Date date, int n) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        gregorianCalendar.add(GregorianCalendar.MONTH, n);
        return gregorianCalendar.getTime();
    }

    /**
     * 返回excel cell在java中日期值
     * @param DateNumber 日期cell 的值
     */
    @SuppressWarnings("static-access")
    public static Date getExcelDateNumberToDate(int DateNumber) {
        //发现excel单元格的日期值1900-1-1值为2 java的月份是0-11表示 故初始值设置如下
        Calendar calendar = new GregorianCalendar(1900, 0, -1);
        calendar.add(calendar.DATE, DateNumber);
        return calendar.getTime();
    }

    /**
     * 时间间隔的分钟数
     */
    public static Long getDateBetweenMinutes(String dateStart, String dateStop, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date d1 = null;
        Date d2 = null;
        StringBuffer sb = new StringBuffer("");
        try {
            d1 = sdf.parse(dateStart);
            if (StringUtils.isBlank(dateStop)) d2 = new Date();
            else d2 = sdf.parse(dateStop);

            //毫秒ms
            long diff = d2.getTime() - d1.getTime();
            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);
            return diffMinutes + diffHours * 60 + diffDays * 24 * 60;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1L;
    }

    /**
     * <b>概要：</b>
     * 返回dateStart和dateStop之间时间差的描述
     * <b>作者：</b>SUXH </br>
     * <b>日期：</b>2015-3-31 </br>
     * @param dateStart 开始时间
     * @param dateStop  结束时间
     * @param format    日期格式
     * @return 时间差的描述
     */
    public static String getDateBetween(String dateStart, String dateStop, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date d1 = null;
        Date d2 = null;
        StringBuffer sb = new StringBuffer("");
        try {
            d1 = sdf.parse(dateStart);
            if (StringUtils.isBlank(dateStop)) {
                d2 = new Date();
            } else {
                d2 = sdf.parse(dateStop);
            }
            long diff = d2.getTime() - d1.getTime();//毫秒ms
            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);
            if (diffDays >= 1) {
                sb.append(diffDays).append("天")
                        .append(diffHours).append("时")
                        .append(diffMinutes).append("分")
                        .append(diffSeconds).append("分");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * <b>概要：</b>
     * 获取minute分钟后日期
     * <b>作者：</b>SUXH </br>
     * <b>日期：</b>2015-3-31 </br>
     * @param minute 多少分钟后
     * @return 多少分钟后的日期
     */
    public static Date getDate4Minute(int minute) {
        Date d = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.MINUTE, minute);
        Date dd = c.getTime();
        return dd;
    }

    /**
     * <b>概要：</b>
     * 获取minute分钟前日期
     * <b>作者：</b>SUXH </br>
     * <b>日期：</b>2015-3-31 </br>
     * @param d      指定日期
     * @param minute 多少分钟前
     * @return 多少分钟前的日期
     */
    public static Date getBefore4Minute(Date d, int minute) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.MINUTE, -minute);
        Date dd = c.getTime();
        return dd;
    }

    /**
     * <b>概要：</b>
     * 判断日期的有效性
     * <b>作者：</b>SUXH </br>
     * <b>日期：</b>2015-3-31 </br>
     * @param dateStr    日期字符串
     * @param dateFormat 日期格式字符串
     * @return true:日期符合格式要求
     *         false:日期不符合格式要求
     */
    public static boolean isValidDate(String dateStr, String dateFormat) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
            simpleDateFormat.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * 获得日期的零点时间
     */
    public static Date getTimeOf24(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, 0);
        return cal.getTime();
    }

    public static String getTimeStamp() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        DateFormat sdf = new SimpleDateFormat(YYYYMMDDHHMMSS);
        return sdf.format(ts);
    }


    public static void main(String[] args) throws Exception {
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
        //List<String> templateCheckStatusArr=TemplateCheckStatus.getValueList();
        // System.out.println(templateCheckStatusArr.contains("0"));
        //System.out.println(DateUtil.isValidDate("2014-03-03","yyyy-MM-dd"));
        System.out.println(DateUtil.isInTimeMinute(new Date(), new Date(), 10));
        System.out.println(SDF.format(DateUtil.getBefore4Minute(new Date(), 4)));
    }
}
