package com.yumi.learn.test.roaring.bitmap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.roaringbitmap.IntConsumer;
import org.roaringbitmap.RoaringBitmap;
import org.roaringbitmap.buffer.ImmutableRoaringBitmap;
import org.roaringbitmap.buffer.MutableRoaringBitmap;
import org.roaringbitmap.longlong.LongBitmapDataProvider;
import org.roaringbitmap.longlong.Roaring64Bitmap;
import org.roaringbitmap.longlong.Roaring64NavigableMap;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
/**
 * (6 roaring bitmap)
 * */
public class RoaringBitmapTest {

	@Test
	void testExamples() {
		RoaringBitmap rr = RoaringBitmap.bitmapOf(1, 2, 3, 1000);

		RoaringBitmap rr2 = new RoaringBitmap();
		// [4000,4255)的所有数
		rr2.add(4000L, 4255L);

		// 可以把bitmap数组中所有不为0的bit位下标放到一个数组中，然后获取给定位置的值
		Assertions.assertEquals(1000, rr.select(3));
		// 返回的是小于等于【给定值】的第一个bit位为1的下标
		Assertions.assertEquals(3, rr.rank(4));
		Assertions.assertEquals(3, rr.rank(3));
		// 是否包含就很简单了
		Assertions.assertTrue(rr.contains(1000));
		Assertions.assertFalse(rr.contains(1001));

		RoaringBitmap rror = RoaringBitmap.or(rr, rr2);
		rr.or(rr2);
		Assertions.assertEquals(rror, rr);

		// 返回的是bitmap中bit位为1的个数，因为bitmap会很大，所以返回的是long类型
		// 1 2 3 1000 [4000,4255)
		Assertions.assertEquals(259L, rr.getLongCardinality());

		// 遍历，最好用forEach，不要用循环
		rr.forEach((IntConsumer) i -> Assertions
			.assertTrue(i == 1 || i == 2 || i == 3 || i == 1000 || (i >= 4000 && i < 4255)));
	}

	@Test
	void testSerializeAndDeserialize() throws Exception {
		RoaringBitmap rb = new RoaringBitmap();
		for (int k = 0; k < 100000; k += 1000) {
			rb.add(k);
		}
		String file1Name = "bitmapwithoutruns.bin";
		try (DataOutputStream out = new DataOutputStream(new FileOutputStream(file1Name))) {
			rb.serialize(out);
		}
		File file1 = new File(file1Name);
		file1.deleteOnExit();
		Assertions.assertTrue(file1.exists());

		// 压缩优化一下
		rb.runOptimize();
		String file2Name = "bitmapwithruns.bin";
		try (DataOutputStream out = new DataOutputStream(new FileOutputStream(file2Name))) {
			rb.serialize(out);
		}
		File file2 = new File(file2Name);
		file2.deleteOnExit();
		Assertions.assertTrue(file2.exists());

		RoaringBitmap rbtest = new RoaringBitmap();
		try (DataInputStream in = new DataInputStream(new FileInputStream(file1Name))) {
			rbtest.deserialize(in);
			Assertions.assertFalse(rbtest.isEmpty());
		}
	}

	@Test
	void testFileMapBitmap() throws Exception {
		MutableRoaringBitmap rr1 = MutableRoaringBitmap.bitmapOf(1, 2, 3, 1000, 1010);

		String file1Name = "bitmapwithoutruns.bin";
		try (DataOutputStream out = new DataOutputStream(new FileOutputStream(file1Name))) {
			rr1.serialize(out);
		}
		File file1 = new File(file1Name);
		file1.deleteOnExit();
		Assertions.assertTrue(file1.exists());

		try (RandomAccessFile randomAccessFile = new RandomAccessFile(file1, "r");
				FileChannel channel = randomAccessFile.getChannel()) {
			MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, file1.length());
			ImmutableRoaringBitmap rrback = new ImmutableRoaringBitmap(buffer);
			rrback.forEach((IntConsumer) i -> System.out.println(i));
		}
	}

	@Test
	void testLongRoaringBitmaps() {
		// 红黑树实现
		LongBitmapDataProvider r = Roaring64NavigableMap.bitmapOf(1, 2, 100, 1000);
		r.addLong(1234);
		Assertions.assertTrue(r.contains(1));
		Assertions.assertFalse(r.contains(3));

		var longIterator = r.getLongIterator();
		while (longIterator.hasNext()) {
			long next = longIterator.next();
			Assertions.assertTrue(next == 1 || next == 2 || next == 100 || next == 1000 || next == 1234);
		}

		// 高48位为key，低16位为Roaring containers
		var bitmap1 = new Roaring64Bitmap();
		var bitmap2 = new Roaring64Bitmap();
		int k = 1 << 16;
		long i = Long.MAX_VALUE / 2;
		long base = i;
		for (; i < base + 10000; ++i) {
			bitmap1.add(i * k);
			bitmap2.add(i * k);
		}
		bitmap1.and(bitmap2);
		Assertions.assertTrue(bitmap2.equals(bitmap1));
	}

	@Test
	void testSizeOfBitMap() {

		// 对比占用空间大小 - 10w元素
		RoaringBitmap roaringBitmap3 = new RoaringBitmap();
		byte[] bits2 = new byte[100000];
		for (int i = 0; i < 100000; i++) {
			roaringBitmap3.add(i);
			bits2[i] = (byte) i;
		}
		System.out.println("10w数据 roaringbitmap byte size:" + roaringBitmap3.getSizeInBytes());
		System.out.println("10w数据 位图数组 byte size:" + bits2.length);

		RoaringBitmap roaringBitmap4 = new RoaringBitmap();
		byte[] bits3 = new byte[1000000];
		for (int i = 0; i < 1000000; i++) {
			roaringBitmap4.add(i);
			bits3[i] = (byte) i;
		}
		System.out.println("100w数据 roaringbitmap byte size:" + roaringBitmap4.getSizeInBytes());
		System.out.println("100w数据 位图数组 byte size:" + bits3.length);

		RoaringBitmap roaringBitmap5 = new RoaringBitmap();
		byte[] bits4 = new byte[10000000];
		for (int i = 0; i < 10000000; i++) {
			roaringBitmap5.add(i);
			bits4[i] = (byte) i;
		}
		System.out.println("1000w数据 roaringbitmap byte size:" + roaringBitmap5.getSizeInBytes());
		System.out.println("1000w数据 位图数组 byte size:" + bits4.length);
	}

	@Test
	public void testSize() {
		RoaringBitmap roaringBitmap5 = new RoaringBitmap();
		byte[] bits4 = new byte[10000000];
		for (int i = 0; i < 10000000; i++) {
			if (i == 1 || i == 9999999) {
				roaringBitmap5.add(i);
				bits4[i] = (byte) i;
			}
		}
		System.out.println("两个稀疏数据 roaringbitmap byte size:" + roaringBitmap5.getSizeInBytes());
		System.out.println("两个稀疏数据 位图数组 byte size:" + bits4.length);
	}

}
