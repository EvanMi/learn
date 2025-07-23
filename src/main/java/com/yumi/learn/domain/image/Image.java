package com.yumi.learn.domain.image;

/**
 * (4) 组合优先于继承
 */
public interface Image {
    /**
     * 调整图像大小。
     *
     * @param width 新的宽度。
     * @param height 新的高度。
     * @return 调整成功返回true，否则返回false。
     */
    boolean resize(int width, int height);

    /**
     * 获取图像的宽度。
     *
     * @return 图像的宽度。
     */
    int getWidth();

    /**
     * 获取图像的高度。
     *
     * @return 图像的高度。
     */
    int getHeight();
    /**
     * 水平翻转图像。
     *
     * @return 翻转成功返回true，否则返回false。
     */
    boolean flipHorizontal();

    /**
     * 垂直翻转图像。
     *
     * @return 翻转成功返回true，否则返回false。
     */
    boolean flipVertical();
}
