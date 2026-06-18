package cn.widdo.study.aigc;

import cn.widdo.study.algorithm.ShortestPath;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * AIGC文生图原理演示系统.
 * 用Java代码可视化"噪声→图像"的每个阶段
 *
 * @author XYL
 * @date 2026/05/29 09:31:33
 */
public class TextToImageSimulator {


    protected TextToImageSimulator() {
        throw new UnsupportedOperationException(ShortestPath.class.getName() + " can`t be instance.");
    }

    // ==================== 1. CLIP: 文本编码器 ====================

    /**
     * CLIP编码器：将人类语言转化为"导航坐标".
     * 真实CLIP输出768维或1024维向量，这里用简化版演示概念
     */
    static class CLIPTextEncoder {

        /**
         * 模拟词嵌入表：每个词对应一个64维概念向量.
         */
        private final java.util.Map<String, double[]> conceptEmbeddings;

        /**
         * random.
         */
        private final Random random;

        /**
         * 文本编码.
         *
         * @param seed 随机种子
         * @author XYL
         * @date 2026/05/11 10:17:38
         */
        CLIPTextEncoder(final long seed) {
            this.random = new Random(seed);
            this.conceptEmbeddings = new java.util.HashMap<>();
            initializeConcepts();
        }

        /**
         * 初始化概念空间（模拟预训练好的词向量）.
         * <p>
         * 每个词对应一个"概念坐标"，相近的词在空间中距离近
         *
         * @author XYL
         * @date 2026/05/11 10:18:01
         */
        private void initializeConcepts() {
            // 动物类概念（在潜空间中聚集在一起）
            conceptEmbeddings.put("猫", createConcept(new double[]{0.8, 0.3, 0.1, 0.9, 0.2, 0.5}));
            conceptEmbeddings.put("橘猫", createConcept(new double[]{0.85, 0.4, 0.15, 0.9, 0.3, 0.6}));
            conceptEmbeddings.put("狗", createConcept(new double[]{0.7, 0.2, 0.3, 0.8, 0.4, 0.3}));

            // 动作/状态概念
            conceptEmbeddings.put("睡觉", createConcept(new double[]{0.1, 0.1, 0.9, 0.2, 0.8, 0.1}));
            conceptEmbeddings.put("奔跑", createConcept(new double[]{0.9, 0.9, 0.1, 0.8, 0.1, 0.9}));
            conceptEmbeddings.put("坐着", createConcept(new double[]{0.3, 0.2, 0.7, 0.4, 0.6, 0.2}));

            // 环境概念
            conceptEmbeddings.put("沙发", createConcept(new double[]{0.4, 0.8, 0.6, 0.3, 0.7, 0.4}));
            conceptEmbeddings.put("阳光", createConcept(new double[]{0.9, 0.9, 0.2, 0.1, 0.3, 0.8}));
            conceptEmbeddings.put("夜晚", createConcept(new double[]{0.1, 0.1, 0.9, 0.8, 0.9, 0.2}));
        }

        /**
         * 创建概念.
         *
         * @param base base
         * @return double[]
         * @author XYL
         * @date 2026/05/11 10:14:52
         */
        private double[] createConcept(double[] base) {
            // 扩展到64维，并添加微小随机扰动（模拟真实嵌入的噪声）
            double[] vec = new double[64];
            for (int i = 0; i < 6 && i < base.length; i++) {
                vec[i] = base[i];
            }
            for (int i = 6; i < 64; i++) {
                vec[i] = random.nextGaussian() * 0.1;
            }
            return vec;
        }

        /**
         * 编码提示词：将文本转化为"条件向量".
         * 就像GPS把地址转化为经纬度坐标
         *
         * @param prompt prompt
         * @return cn.widdo.study.aigc.TextToImageSimulator.ConditioningVector
         * @author XYL
         * @date 2026/05/11 10:18:38
         */
        public ConditioningVector encode(String prompt) {
            System.out.println("\n📝 [CLIP编码] 输入: \"" + prompt + "\"");

            String[] words = prompt.split("[,，\\s]+");
            double[] combined = new double[64];

            for (String word : words) {
                double[] embedding = conceptEmbeddings.getOrDefault(word,
                        createRandomConcept()); // OOV词用随机概念模拟

                System.out.println("   词 \"" + word + "\" → 概念向量(前6维): "
                        + formatVector(embedding, 6));

                // 向量叠加（模拟注意力机制的简化版）
                for (int i = 0; i < 64; i++) {
                    combined[i] += embedding[i];
                }
            }

            // 归一化（L2 Norm）
            double norm = 0;
            for (double v : combined) {
                norm += v * v;
            }
            norm = Math.sqrt(norm);
            for (int i = 0; i < 64; i++) {
                combined[i] /= norm;
            }

            System.out.println("   融合后条件向量(前6维): " + formatVector(combined, 6));
            return new ConditioningVector(combined);
        }

        /**
         * 创建概念.
         *
         * @return double[]
         * @author XYL
         * @date 2026/05/11 10:18:55
         */
        private double[] createRandomConcept() {
            double[] vec = new double[64];
            for (int i = 0; i < 64; i++) {
                vec[i] = random.nextGaussian();
            }
            return vec;
        }

        /**
         * formatVector.
         *
         * @param vec vec
         * @param n   n
         * @return java.lang.String
         * @author XYL
         * @date 2026/05/11 10:19:28
         */
        private String formatVector(double[] vec, int n) {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < n && i < vec.length; i++) {
                sb.append(String.format("%.2f", vec[i]));
                if (i < n - 1) {
                    sb.append(", ");
                }
            }
            sb.append("...]");
            return sb.toString();
        }
    }

    /**
     * 条件向量：CLIP输出的"导航坐标".
     * 告诉UNET"往哪个方向去噪"
     *
     * @author XYL
     * @date 2026/05/11 10:20:02
     */
    static class ConditioningVector {

        /**
         * vector.
         */
        private final double[] vector;  // 64维概念坐标

        /**
         * v.
         *
         * @param v v
         */
        ConditioningVector(final double[] v) {
            this.vector = v;
        }
    }

    // ==================== 2. 噪声生成器 ====================

    /**
     * 噪声生成器：创建初始"大理石块".
     * 真实潜空间是64×64×4，这里用8×8简化演示
     */
    static class NoiseGenerator {

        /**
         * random.
         */
        private final Random random;

        /**
         * seed.
         *
         * @param seed 随机种子
         */
        NoiseGenerator(final long seed) {
            this.random = new Random(seed);
        }


        /**
         * 生成初始潜空间噪声.
         * 这就是"大理石块"——包含所有可能的结构，但看起来是混沌的
         *
         * @param width    width
         * @param height   height
         * @param channels channels
         * @return cn.widdo.study.aigc.TextToImageSimulator.LatentSpace
         * @author XYL
         * @date 2026/05/27 11:46:07
         */
        public LatentSpace generate(int width, int height, int channels) {
            System.out.println("\n🎲 [噪声生成] 创建 " + width + "×" + height + "×" + channels + " 的随机潜空间");

            double[][][] noise = new double[channels][height][width];
            for (int c = 0; c < channels; c++) {
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        // 标准正态分布 N(0,1) —— 扩散模型的标准初始化
                        noise[c][y][x] = random.nextGaussian();
                    }
                }
            }

            System.out.println("   噪声统计: 均值≈0, 标准差≈1");
            System.out.println("   可视化: 完全随机的灰度雪花（潜空间中的'混沌'）");

            return new LatentSpace(noise, width, height, channels);
        }
    }

    // ==================== 3. UNET: 去噪核心 ====================

    /**
     * UNET网络：模拟去噪过程.
     * 真实UNET有上亿参数，这里用简化的"条件引导去噪"演示原理
     */
    static class UNET {

        /**
         * random.
         */
        private final Random random;

        /**
         * 随机种子.
         *
         * @param seed 随机种子
         */
        UNET(final long seed) {
            this.random = new Random(seed);
        }

        /**
         * 去噪一步：从当前状态预测噪声，然后减去.
         *
         * @param latent       当前潜空间状态
         * @param conditioning CLIP提供的条件向量（导航坐标）
         * @param timestep     当前时间步（0=干净, 1000=纯噪声）
         * @param cfgScale     CFG值（提示词服从度）
         * @return cn.widdo.study.aigc.TextToImageSimulator.LatentSpace
         * @author XYL
         * @date 2026/05/27 11:52:56
         */
        public LatentSpace denoiseStep(LatentSpace latent, ConditioningVector conditioning,
                                       int timestep, double cfgScale) {

            // 1. 预测当前 latent 中的噪声成分（模拟UNET前向传播）
            double[][][] predictedNoise = predictNoise(latent, timestep);

            // 2. 条件引导（CFG的核心）：让去噪方向偏向提示词描述
            double[][][] guidedNoise = applyCFG(predictedNoise, conditioning, cfgScale);

            // 3. 计算去噪强度（由timestep决定，早期去噪快，后期精细调整）
            double alpha = calculateAlpha(timestep);

            // 4. 执行去噪：latent = latent - alpha * guidedNoise
            return latent.subtract(guidedNoise, alpha);
        }

        /**
         * 模拟UNET预测噪声：真实网络通过卷积层学习这个函数.
         * 这里用启发式规则模拟"如果知道是猫，就去掉不像猫的噪声"
         *
         * @param latent   latent
         * @param timestep timestep
         * @return double[][][] double
         * @author XYL
         * @date 2026/05/27 11:53:13
         */
        private double[][][] predictNoise(LatentSpace latent, int timestep) {
            int channels = latent.channels;
            int height = latent.height;
            int width = latent.width;
            double[][][] noise = new double[channels][height][width];

            // 模拟：时间步越大，预测的噪声越"粗糙"（早期步骤）
            // 时间步越小，预测越"精细"（后期步骤）
            double noiseScale = 0.1 + (timestep / 1000.0) * 0.5;

            for (int c = 0; c < channels; c++) {
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        // 模拟残差预测：UNET学习的是"应该去掉什么"
                        noise[c][y][x] = latent.data[c][y][x] * noiseScale
                                + random.nextGaussian() * 0.05;
                    }
                }
            }
            return noise;
        }

        /**
         * CFG核心：Classifier-Free Guidance.
         * <p>
         * 原理：同时计算"有条件"和"无条件"的噪声预测，然后按CFG缩放差值
         * <p>
         * guided = uncond + cfg * (cond - uncond)
         * <p>
         * 就像：基础驾驶 + CFG倍数的"向目标偏移"
         *
         * @param predictedNoise predictedNoise
         * @param conditioning   conditioning
         * @param cfgScale       cfgScale
         * @return double[][][] double
         * @author XYL
         * @date 2026/05/27 11:53:50
         */
        private double[][][] applyCFG(double[][][] predictedNoise,
                                      ConditioningVector conditioning,
                                      double cfgScale) {
            int channels = predictedNoise.length;
            int height = predictedNoise[0].length;
            int width = predictedNoise[0][0].length;

            double[][][] guided = new double[channels][height][width];

            // 无条件预测（模拟：把条件向量置零后的预测）
            // 这里简化：假设无条件就是当前预测的"平均化"版本
            double uncondWeight = 1.0;
            double condWeight = cfgScale;

            // 将64维条件向量映射到空间维度（简化：用条件向量的统计特征影响空间）
            double conceptStrength = 0;
            for (double v : conditioning.vector) {
                conceptStrength += Math.abs(v);
            }
            conceptStrength /= conditioning.vector.length;

            for (int c = 0; c < channels; c++) {
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        double uncond = predictedNoise[c][y][x] * uncondWeight;
                        // 条件引导：让噪声预测偏向"符合提示词"的方向
                        double condBias = predictedNoise[c][y][x] * conceptStrength * 0.3;

                        // CFG公式：无条件 + scale × (有条件 - 无条件)
                        guided[c][y][x] = uncond + cfgScale * condBias;
                    }
                }
            }

            return guided;
        }

        /**
         * 模拟扩散模型的alpha调度.
         *
         * @param timestep timestep
         * @return double double
         * @author XYL
         * @date 2026/05/28 17:14:08
         */
        private double calculateAlpha(int timestep) {
            // 模拟扩散模型的alpha调度
            // 早期timestep大，去噪步长大；后期timestep小，精细调整
            return 0.02 + 0.08 * (timestep / 1000.0);
        }
    }

    // ==================== 4. VAE解码器 ====================

    /**
     * VAE解码器：将64×64潜空间放大到512×512图像.
     * 真实VAE用转置卷积上采样，这里用插值+特征映射模拟
     */
    static class VAEDecoder {

        /**
         * 解码潜空间为可视图像.
         * 真实过程：转置卷积层学习将特征通道映射为RGB
         *
         * @param latent     latent
         * @param targetSize targetSize
         * @return java.awt.image.BufferedImage
         * @author XYL
         * @date 2026/05/28 17:15:08
         */
        public BufferedImage decode(LatentSpace latent, int targetSize) {
            System.out.println("\n🖼️  [VAE解码] " + latent.width + "×" + latent.height
                    + " 潜空间 → " + targetSize + "×" + targetSize + " 图像");

            // 1. 将潜空间通道压缩为RGB（模拟卷积层的通道混合）
            double[][][] rgbFeatures = latentToRGBFeatures(latent);

            // 2. 上采样到目标尺寸（模拟转置卷积）
            double[][][] upsampled = upsample(rgbFeatures, targetSize);

            // 3. 后处理：添加细节、调整对比度（模拟VAE的后置网络）
            double[][][] finalImage = postProcess(upsampled);

            // 4. 转换为BufferedImage
            return toBufferedImage(finalImage);
        }

        /**
         * latentToRGBFeatures.
         *
         * @param latent latent
         * @return double[][][]
         * @author XYL
         * @date 2026/05/28 17:15:32
         */
        private double[][][] latentToRGBFeatures(LatentSpace latent) {

            int channels = latent.height;
            int width = latent.width;
            // 4通道潜空间 → 3通道RGB（模拟1×1卷积）
            double[][][] rgb = new double[3][channels][width];

            for (int y = 0; y < channels; y++) {
                for (int x = 0; x < width; x++) {
                    // 模拟卷积核：不同通道组合产生颜色
                    rgb[0][y][x] = latent.data[0][y][x] * 0.5 + latent.data[2][y][x] * 0.5; // R
                    rgb[1][y][x] = latent.data[1][y][x] * 0.6 + latent.data[3][y][x] * 0.4; // G
                    rgb[2][y][x] = latent.data[0][y][x] * 0.3 + latent.data[1][y][x] * 0.3
                            + latent.data[2][y][x] * 0.2 + latent.data[3][y][x] * 0.2; // B
                }
            }
            return rgb;
        }

        /**
         * upsample.
         *
         * @param features   features
         * @param targetSize targetSize
         * @return double[][][]
         * @author XYL
         * @date 2026/05/28 17:15:42
         */
        private double[][][] upsample(double[][][] features, int targetSize) {
            int channels = features.length;
            int height = features[0].length;
            int width = features[0][0].length;
            double[][][] result = new double[channels][targetSize][targetSize];

            // 双线性插值上采样（模拟转置卷积的简化版）
            double scaleY = (double) (height - 1) / (targetSize - 1);
            double scaleX = (double) (width - 1) / (targetSize - 1);

            for (int c = 0; c < channels; c++) {
                for (int y = 0; y < targetSize; y++) {
                    for (int x = 0; x < targetSize; x++) {
                        double srcY = y * scaleY;
                        double srcX = x * scaleX;

                        int y0 = (int) srcY;
                        int y1 = Math.min(y0 + 1, height - 1);
                        int x0 = (int) srcX;
                        int x1 = Math.min(x0 + 1, width - 1);

                        double dy = srcY - y0;
                        double dx = srcX - x0;

                        // 双线性插值
                        result[c][y][x] =
                                features[c][y0][x0] * (1 - dx) * (1 - dy)
                                        + features[c][y0][x1] * dx * (1 - dy)
                                        + features[c][y1][x0] * (1 - dx) * dy
                                        + features[c][y1][x1] * dx * dy;
                    }
                }
            }
            return result;
        }

        /**
         * postProcess.
         *
         * @param image image
         * @return double[][][]
         * @author XYL
         * @date 2026/05/28 17:15:52
         */
        private double[][][] postProcess(double[][][] image) {
            int channels = image.length;
            int height = image[0].length;
            int width = image[0][0].length;

            // 模拟VAE后处理：添加高频细节、色彩校正
            for (int c = 0; c < channels; c++) {
                // 计算直方图，做对比度拉伸
                double min = Double.MAX_VALUE;
                double max = -Double.MAX_VALUE;
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        min = Math.min(min, image[c][y][x]);
                        max = Math.max(max, image[c][y][x]);
                    }
                }

                // 归一化到[0, 255]
                double range = max - min;
                if (range < 0.001) {
                    range = 0.001; // 防止除零
                }

                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        image[c][y][x] = ((image[c][y][x] - min) / range) * 255.0;
                        // 添加微小噪声模拟真实感（dithering）
                        image[c][y][x] += (Math.random() - 0.5) * 2;
                        image[c][y][x] = Math.max(0, Math.min(255, image[c][y][x]));
                    }
                }
            }
            return image;
        }

        /**
         * toBufferedImage.
         *
         * @param image image
         * @return java.awt.image.BufferedImage
         * @author XYL
         * @date 2026/05/28 17:16:02
         */
        private BufferedImage toBufferedImage(double[][][] image) {
            int height = image[0].length;
            int width = image[0][0].length;
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int r = (int) Math.max(0, Math.min(255, image[0][y][x]));
                    int g = (int) Math.max(0, Math.min(255, image[1][y][x]));
                    int b = (int) Math.max(0, Math.min(255, image[2][y][x]));
                    bi.setRGB(x, y, (r << 16) | (g << 8) | b);
                }
            }
            return bi;
        }
    }

    // ==================== 5. 潜空间数据结构 ====================

    static class LatentSpace {
        /**
         * data.
         */
        private final double[][][] data;

        /**
         * width.
         */
        private final int width;

        /**
         * height.
         */
        private final int height;

        /**
         * channels.
         */
        private final int channels;

        /**
         * LatentSpace.
         *
         * @param d d
         * @param w w
         * @param h h
         * @param c c
         * @author XYL
         * @date 2026/05/28 17:37:59
         */
        LatentSpace(final double[][][] d, final int w, final int h, final int c) {
            this.data = d;
            this.width = w;
            this.height = h;
            this.channels = c;
        }

        /**
         * 执行去噪：减去缩放后的噪声.
         *
         * @param noise noise
         * @param alpha alpha
         * @return cn.widdo.study.aigc.TextToImageSimulator.LatentSpace
         * @author XYL
         * @date 2026/05/28 17:30:54
         */
        LatentSpace subtract(double[][][] noise, double alpha) {
            double[][][] result = new double[channels][height][width];
            for (int c = 0; c < channels; c++) {
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        result[c][y][x] = data[c][y][x] - alpha * noise[c][y][x];
                    }
                }
            }
            return new LatentSpace(result, width, height, channels);
        }

        /**
         * 可视化潜空间（将4通道压缩为RGB预览）.
         *
         * @param size size
         * @return java.awt.image.BufferedImage
         * @author XYL
         * @date 2026/05/28 17:30:37
         */
        BufferedImage toPreview(int size) {
            return new VAEDecoder().decode(this, size);
        }
    }

    // ==================== 6. 主流程控制器 ====================

    static class TextToImagePipeline {

        /**
         * clip.
         */
        private final CLIPTextEncoder clip;

        /**
         * noiseGen.
         */
        private final NoiseGenerator noiseGen;

        /**
         * unet.
         */
        private final UNET unet;

        /**
         * vae.
         */
        private final VAEDecoder vae;

        /**
         * TextToImagePipeline.
         *
         * @param seed seed
         * @author XYL
         * @date 2026/05/28 17:38:18
         */
        TextToImagePipeline(final long seed) {
            this.clip = new CLIPTextEncoder(seed);
            this.noiseGen = new NoiseGenerator(seed);
            this.unet = new UNET(seed);
            this.vae = new VAEDecoder();
        }

        /**
         *
         * 完整生成流程.
         *
         * @param prompt   提示词
         * @param numSteps 去噪步数（20-50）
         * @param cfgScale CFG值（通常7-12）
         * @param seed     随机种子（复现用）
         * @return cn.widdo.study.aigc.TextToImageSimulator.GenerationResult
         * @author XYL
         * @date 2026/05/28 17:40:10
         */
        public GenerationResult generate(String prompt, int numSteps,
                                         double cfgScale, long seed) {

            System.out.println("╔══════════════════════════════════════════════════════════╗");
            System.out.println("║     AIGC 文生图原理演示 - Java可视化版                     ║");
            System.out.println("║     提示词: \"" + prompt + "\"");
            System.out.println("║     参数: steps=" + numSteps + ", CFG=" + cfgScale + ", seed=" + seed);
            System.out.println("╚══════════════════════════════════════════════════════════╝");

            // Stage 1: CLIP编码（文本→坐标）
            ConditioningVector conditioning = clip.encode(prompt);

            // Stage 2: 生成初始噪声（大理石块）
            LatentSpace latent = noiseGen.generate(8, 8, 4); // 8×8×4简化潜空间

            // 保存初始状态用于可视化
            BufferedImage[] stepImages = new BufferedImage[numSteps + 1];
            stepImages[0] = latent.toPreview(256);

            // Stage 3: UNET迭代去噪（雕刻过程）
            System.out.println("\n🔧 [UNET去噪] 开始 " + numSteps + " 步迭代...");

            int totalTimesteps = 1000;
            int[] timestepSchedule = createSchedule(numSteps, totalTimesteps);

            for (int i = 0; i < numSteps; i++) {
                int t = timestepSchedule[i];

                // 核心去噪步骤
                latent = unet.denoiseStep(latent, conditioning, t, cfgScale);

                // 每5步保存预览
                if (i % 5 == 4 || i == numSteps - 1) {
                    stepImages[i + 1] = latent.toPreview(256);
                    System.out.println("   Step " + (i + 1) + "/" + numSteps
                            + " (timestep=" + t + ") - 结构逐渐清晰...");
                }
            }

            // Stage 4: VAE解码（最终渲染）
            System.out.println("\n✨ [完成] 生成最终图像");
            BufferedImage finalImage = vae.decode(latent, 512);

            return new GenerationResult(finalImage, stepImages, prompt, numSteps, cfgScale);
        }

        /**
         *
         * 创建时间步调度（模拟DDPM的cosine调度）.
         *
         * @param numSteps numSteps
         * @param maxT     maxT
         * @return int[]
         * @author XYL
         * @date 2026/05/28 17:26:51
         */
        private int[] createSchedule(int numSteps, int maxT) {
            int[] schedule = new int[numSteps];
            for (int i = 0; i < numSteps; i++) {
                // 从大到小：先粗雕，后细雕
                double progress = (double) i / numSteps;
                schedule[i] = (int) (maxT * (1 - Math.pow(progress, 0.5)));
            }
            return schedule;
        }
    }

    static class GenerationResult {

        /**
         * finalImage.
         */
        private final BufferedImage finalImage;

        /**
         * stepImages.
         */
        private final BufferedImage[] stepImages;

        /**
         * prompt.
         */
        private final String prompt;

        /**
         * steps.
         */
        private final int steps;

        /**
         * cfg.
         */
        private final double cfg;

        /**
         * 构造方法.
         *
         * @param fin   fin
         * @param steps steps
         * @param p     p
         * @param s     s
         * @param c     c
         */
        GenerationResult(final BufferedImage fin, final BufferedImage[] steps,
                         final String p, final int s, final double c) {
            this.finalImage = fin;
            this.stepImages = steps;
            this.prompt = p;
            this.steps = s;
            this.cfg = c;
        }
    }

    // ==================== 7. 可视化与输出 ====================

    static class Visualizer {

        /**
         *
         * 创建去噪过程对比图.
         *
         * @param result result
         * @return java.awt.image.BufferedImage
         * @author XYL
         * @date 2026/05/28 17:24:53
         */
        public static BufferedImage createProcessGrid(GenerationResult result) {
            int cols = 5; // 显示5个关键步骤
            int thumbSize = 200;
            int padding = 10;

            BufferedImage grid = new BufferedImage(
                    cols * (thumbSize + padding) + padding,
                    thumbSize + 80,
                    BufferedImage.TYPE_INT_RGB
            );
            Graphics2D g = grid.createGraphics();
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, grid.getWidth(), grid.getHeight());

            // 选择关键帧
            int[] keyIndices = {0, result.stepImages.length / 4,
                    result.stepImages.length / 2,
                    result.stepImages.length * 3 / 4,
                    result.stepImages.length - 1};

            for (int i = 0; i < cols; i++) {
                int idx = Math.min(keyIndices[i], result.stepImages.length - 1);
                BufferedImage thumb = result.stepImages[idx];
                if (thumb != null) {
                    g.drawImage(thumb,
                            padding + i * (thumbSize + padding),
                            20, thumbSize, thumbSize, null);
                }

                // 标签
                g.setColor(Color.WHITE);
                g.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                String label = (idx == 0) ? "初始噪声" : "Step " + idx + "/" + result.steps;
                g.drawString(label,
                        padding + i * (thumbSize + padding) + 20,
                        thumbSize + 45);
            }

            // 标题
            g.setFont(new Font("微软雅黑", Font.BOLD, 14));
            g.drawString("去噪过程可视化: \"" + result.prompt + "\" (CFG=" + result.cfg + ")",
                    padding, 15);

            g.dispose();
            return grid;
        }
    }

    // ==================== 8. 主程序入口 ====================

    public static void main(String[] args) throws Exception {
        // 创建管道（固定种子保证可复现）
        long seed = 42;
        TextToImagePipeline pipeline = new TextToImagePipeline(seed);

        // 实验1：标准参数
        System.out.println("\n" + "═".repeat(60));
        System.out.println("实验1: 标准参数 (steps=30, CFG=7.0)");
        System.out.println("═".repeat(60));

        GenerationResult result1 = pipeline.generate(
                "橘猫, 睡觉, 沙发", 30, 7.0, seed);

        // 实验2：高CFG（更服从提示词）
        System.out.println("\n" + "═".repeat(60));
        System.out.println("实验2: 高CFG (steps=30, CFG=12.0)");
        System.out.println("═".repeat(60));

        GenerationResult result2 = pipeline.generate(
                "橘猫, 睡觉, 沙发", 30, 12.0, seed);

        // 实验3：少步数（快速但粗糙）
        System.out.println("\n" + "═".repeat(60));
        System.out.println("实验3: 快速生成 (steps=10, CFG=7.0)");
        System.out.println("═".repeat(60));

        GenerationResult result3 = pipeline.generate(
                "橘猫, 睡觉, 沙发", 10, 7.0, seed);

        // 保存结果
        saveImage(result1.finalImage, "output_cfg7.png");
        saveImage(result2.finalImage, "output_cfg12.png");
        saveImage(result3.finalImage, "output_fast.png");

        // 保存过程可视化
        saveImage(Visualizer.createProcessGrid(result1), "process_cfg7.png");
        saveImage(Visualizer.createProcessGrid(result2), "process_cfg12.png");

        System.out.println("\n✅ 所有图像已保存!");
        System.out.println("注意：这是原理演示，非真实模型输出。");
        System.out.println("真实效果需要加载Stable Diffusion的预训练权重（约4GB）");
    }

    /**
     * saveImage.
     *
     * @param img      img
     * @param filename filename
     * @author XYL
     * @date 2026/05/28 17:24:16
     */
    static void saveImage(BufferedImage img, String filename) throws Exception {
        javax.imageio.ImageIO.write(img, "PNG",
                new java.io.File("/mnt/agents/output/" + filename));
        System.out.println("💾 已保存: " + filename);
    }
}
