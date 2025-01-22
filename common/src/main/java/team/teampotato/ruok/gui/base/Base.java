package team.teampotato.ruok.gui.base;

import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;
import java.util.function.Function;
/**
 * 这不是API...绝对不是! 真的
 */
public class Base<S, T> {
    String key;
    BiConsumer<S, T> biConsumer;
    Function<S, T> function;
    BaseUtil.Type type;
    BaseUtil.Group group;
    int interVal;
    int min;
    int max;
    int rank;
    Component[] texts;
    String format;

    public String getFormat() {
        return format;
    }

    public int getRank() {
        return rank;
    }

    public Component[] getTexts() {
        return texts;
    }

    public int getInterVal() {
        return interVal;
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    public BaseUtil.Group getGroup() {
        return group;
    }

    public BaseUtil.Type getType() {
        return type;
    }

    public BiConsumer<S, T> getBiConsumer() {
        return biConsumer;
    }

    public Function<S, T> getFunction() {
        return function;
    }

    public String getKey() {
        return key;
    }


    /**
     * 传入一些参数做一个通用按钮.用来返回一个无需按Rank排序的Base方法
     *
     * @param key 注册名称 + 工具提示栏
     * @param biConsumer 传入需要返回设置值,需要传入2个参数(其中一个参数在原版界面不会被使用,可以作废一个参数)
     * @param function 传入当前的值,来显示当前的值
     * @param group Base使用的组,这个适用于钠界面的分类
     * @param type 类型,用来标记是Int或Boolean(但应该没啥用)
     *
     */
    public Base(String key, BiConsumer<S, T> biConsumer, Function<S, T> function, BaseUtil.Type type, BaseUtil.Group group) {
        this.key = key;
        this.biConsumer = biConsumer;
        this.function = function;
        this.type = type;
        this.group = group;
    }
    /**
     * 传入一些参数做一个通用按钮.用来返回一个需按Rank排序的Base方法
     *
     * @param key 注册名称 + 工具提示栏
     * @param biConsumer 传入需要返回设置值,需要传入2个参数(其中一个参数在原版界面不会被使用,可以作废一个参数)
     * @param function 传入当前的值,来显示当前的值
     * @param group Base使用的组,这个适用于钠界面的分类
     * @param type 类型,用来标记是Int或Boolean(但应该没啥用)
     * @param rank 排序数字,从0开始,0为开头列表第一个
     *
     */
    public Base(String key, BiConsumer<S, T> biConsumer, Function<S, T> function, BaseUtil.Type type, BaseUtil.Group group,int rank) {
        this.key = key;
        this.biConsumer = biConsumer;
        this.function = function;
        this.type = type;
        this.group = group;
        this.rank = rank;
    }
    /**
     * 传入一些参数做一个通用按钮.用来返回一个无需按Rank排序的Base方法
     *
     * @param key 注册名称 + 工具提示栏
     * @param biConsumer 传入需要返回设置值,需要传入2个参数(其中一个参数在原版界面不会被使用,可以作废一个参数)
     * @param function 传入当前的值,来显示当前的值
     * @param group Base使用的组,这个适用于钠界面的分类
     * @param type 类型,用来标记是Int或Boolean(但应该没啥用)
     * @param max 按钮最大的数字值
     * @param min 按钮最小的数字值
     * @param interVal 一次增加多少值的数字值
     *
     */
    public Base(String key, BiConsumer<S, T> biConsumer, Function<S, T> function, BaseUtil.Type type, BaseUtil.Group group, int interVal, int min, int max,String format) {
        this.key = key;
        this.biConsumer = biConsumer;
        this.function = function;
        this.type = type;
        this.group = group;
        this.interVal = interVal;
        this.min = min;
        this.max = max;
        this.format = format;
    }
    /**
     * 传入一些参数做一个通用按钮.用来返回一个需按Rank排序的Base方法
     *
     * @param key 注册名称 + 工具提示栏
     * @param biConsumer 传入需要返回设置值,需要传入2个参数(其中一个参数在原版界面不会被使用,可以作废一个参数)
     * @param function 传入当前的值,来显示当前的值
     * @param group Base使用的组,这个适用于钠界面的分类
     * @param type 类型,用来标记是Int或Boolean(但应该没啥用)
     * @param max 按钮最大的数字值
     * @param min 按钮最小的数字值
     * @param interVal 一次增加多少值的数字值
     * @param rank 排序数字,从0开始,0为开头列表第一个
     *
     */
    public Base(String key, BiConsumer<S, T> biConsumer, Function<S, T> function, BaseUtil.Type type, BaseUtil.Group group, int interVal, int min, int max,String format,int rank) {
        this.key = key;
        this.biConsumer = biConsumer;
        this.function = function;
        this.type = type;
        this.group = group;
        this.interVal = interVal;
        this.min = min;
        this.max = max;
        this.format = format;
        this.rank = rank;
    }
    /**
     * 传入一些参数做一个通用按钮.用来返回一个无需按Rank排序的Base方法
     *
     * @param key 注册名称 + 工具提示栏
     * @param biConsumer 传入需要返回设置值,需要传入2个参数(其中一个参数在原版界面不会被使用,可以作废一个参数)
     * @param function 传入当前的值,来显示当前的值
     * @param group Base使用的组,这个适用于钠界面的分类
     * @param type 类型,用来标记是Int或Boolean(但应该没啥用)
     * @param texts 用于枚举的Text的显示
     *
     */
    public Base(String key, BiConsumer<S, T> biConsumer, Function<S, T> function, BaseUtil.Type type, BaseUtil.Group group, Component[] texts) {
        this.key = key;
        this.biConsumer = biConsumer;
        this.function = function;
        this.type = type;
        this.group = group;
        this.texts = texts;
    }
    /**
     * 传入一些参数做一个通用按钮.用来返回一个需按Rank排序的Base方法
     *
     * @param key 注册名称 + 工具提示栏
     * @param biConsumer 传入需要返回设置值,需要传入2个参数(其中一个参数在原版界面不会被使用,可以作废一个参数)
     * @param function 传入当前的值,来显示当前的值
     * @param group Base使用的组,这个适用于钠界面的分类
     * @param type 类型,用来标记是Int或Boolean(但应该没啥用)
     * @param rank 排序数字,从0开始,0为开头列表第一个
     * @param texts 用于枚举的Text的显示
     *
     */
    public Base(String key, BiConsumer<S, T> biConsumer, Function<S, T> function, BaseUtil.Type type, BaseUtil.Group group,Component[] texts,int rank) {
        this.key = key;
        this.biConsumer = biConsumer;
        this.function = function;
        this.type = type;
        this.group = group;
        this.texts = texts;
        this.rank = rank;
    }
    public Base(String key, BiConsumer<S, T> biConsumer, Function<S, T> function, BaseUtil.Type type, BaseUtil.Group group, int interVal, int min, int max,Component[] texts,int rank) {
        this.key = key;
        this.biConsumer = biConsumer;
        this.function = function;
        this.type = type;
        this.group = group;
        this.interVal = interVal;
        this.min = min;
        this.max = max;
        this.rank = rank;
        this.texts = texts;
    }
    public Base(@NotNull Base<String, T> ob) {
        this.key = ob.getKey();
        this.biConsumer = (str, x) -> ob.getBiConsumer().accept(str.toString(), x);
        this.function = (s) -> ob.getFunction().apply(s.toString());
        this.type = ob.getType();
        this.group = ob.getGroup();
        this.interVal = ob.getInterVal();
        this.min = ob.getMin();
        this.max = ob.getMax();
        this.rank = ob.getRank();
        this.texts = ob.getTexts();
        this.format = ob.getFormat();
    }

}