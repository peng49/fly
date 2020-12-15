package fly.frontend.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class AvatarUtils {

    private static final int GITHUB_AVATAR_ROWS = 420;

    private static final int GITHUB_AVATAR_COLS = 420;

    // 选出一些大概会比较好看的颜色池用于生成
    private static final int[][] COLOR_POOL_RGB = new int[][]{
            {170, 205, 102},
            {159, 255, 84},
            {209, 206, 0},
            {255, 255, 0},
            {47, 107, 85},
            {47, 255, 173},
            {0, 173, 205},
            {8, 101, 139},
            {180, 180, 238},
            {106, 106, 255},
            {155, 211, 255},
            {204, 50, 153},
            {101, 119, 139}
    };
    // 外围宽度
    private static final int GITHUB_AVATAR_FRAME_WIDTH = 35;
    // Block宽度
    private static final int GITHUB_AVATAR_BLOCK_WIDTH = 70;

    private static final int GITHUB_AVATAR_BLOCK_HEIGHT = 70;

    // Vertex 大小
    private static final int GITHUB_AVATAR_VERTEX_WIDTH = 5;

    /**
     * 获取一个 5x5 的随机填充对称矩阵
     *
     * @return 5x5 随机填充对称矩阵
     */
    private boolean[][] getGithubAvatarVertex() {
        // 新建矩阵
        boolean[][] vertex = new boolean[5][5];

        // 先随机填充中间一条
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            if (random.nextBoolean()) {
                vertex[i][2] = true;
            }
        }

        // 随机填充半边
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                if (random.nextBoolean()) {
                    vertex[i][j] = true;
                }
            }
        }

        // 将填充的半边对称复制到另外半边
        for (int i = 0; i < 5; i++) {
            for (int j = 3; j < 5; j++) {
                vertex[i][j] = vertex[i][4 - j];
            }
        }

        return vertex;
    }


    /**
     *  获取一个随机头像
     * @return
     */
    public BufferedImage getARandomAvatar() {
        BufferedImage bi = new BufferedImage(GITHUB_AVATAR_ROWS, GITHUB_AVATAR_COLS, BufferedImage.TYPE_INT_ARGB);
        Graphics2D ig2 = bi.createGraphics();

        Random random = new Random();

        //设置背景颜色 230, 230, 230
//        ig2.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
        ig2.setColor(new Color(230,230,230));
        ig2.fillRect(0, 0, GITHUB_AVATAR_ROWS, GITHUB_AVATAR_COLS);

        boolean[][] vertex = getGithubAvatarVertex();

//        int[] rgb = COLOR_POOL_RGB[random.nextInt(COLOR_GREY_BGR.length)];
        Color color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));

        //填充中间一列的颜色
        for (int i = 0; i < 5; i++) {
            if (vertex[i][2]) {
                ig2.setColor(color);
                ig2.fillRect(
                        GITHUB_AVATAR_FRAME_WIDTH + 2 * GITHUB_AVATAR_BLOCK_WIDTH,
                        GITHUB_AVATAR_FRAME_WIDTH + i * GITHUB_AVATAR_BLOCK_HEIGHT,
                        GITHUB_AVATAR_BLOCK_WIDTH,
                        GITHUB_AVATAR_BLOCK_HEIGHT
                );
            }
        }

        //填充前两列的颜色
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                if (vertex[j][i]) {
                    ig2.setColor(color);
                    ig2.fillRect(
                            GITHUB_AVATAR_FRAME_WIDTH + j * GITHUB_AVATAR_BLOCK_WIDTH,
                            GITHUB_AVATAR_FRAME_WIDTH + i * GITHUB_AVATAR_BLOCK_HEIGHT,
                            GITHUB_AVATAR_BLOCK_WIDTH,
                            GITHUB_AVATAR_BLOCK_HEIGHT
                    );

                    //填充对称位置
                    ig2.fillRect(
                            GITHUB_AVATAR_FRAME_WIDTH + (4 - j) * GITHUB_AVATAR_BLOCK_WIDTH,
                            GITHUB_AVATAR_FRAME_WIDTH + i * GITHUB_AVATAR_BLOCK_HEIGHT,
                            GITHUB_AVATAR_BLOCK_WIDTH,
                            GITHUB_AVATAR_BLOCK_HEIGHT);
                }
            }
        }
        return bi;
    }
}
