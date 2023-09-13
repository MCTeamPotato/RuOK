package team.teampotato.ruok.forge.client;

/*
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;


public class ProgressToast implements Toast {

    // 定义一个文本字段，用于存储进度信息
    private final Text progressText;

    // 定义一个构造方法，用于初始化进度信息
    public ProgressToast(String progress) {
        // 调用父类的构造方法，设置提示栏的类型和持续时间
       // super(SystemToast.Type.TUTORIAL_HINT, Text.literal("Progress"), null, 6000);
        super();
        // 设置进度信息为参数传入的字符串
        this.progressText = Text.translatable(progress);
    }

    // 重写render方法，用于绘制提示栏的内容
    @Override
    public Visibility draw(MatrixStack matrices, ToastManager manager, long startTime) {
        // 使用manager.drawTexture方法来绘制提示栏的背景和图标
        manager.drawTexture(matrices, 0, 0, 0, 0, this.getWidth(), this.getHeight());
        // 获取MinecraftClient实例
        MinecraftClient client = MinecraftClient.getInstance();
        // 在提示栏上绘制进度信息
        client.textRenderer.draw(matrices, this.progressText, 30.0F, 12.0F, -1);
        // 返回提示栏的可见性
        return startTime < 5000L ? Visibility.SHOW : Visibility.HIDE;
    }


}

 */
