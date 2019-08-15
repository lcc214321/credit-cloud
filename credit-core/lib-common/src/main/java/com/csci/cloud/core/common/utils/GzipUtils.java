package com.csci.cloud.core.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.zip.GZIPOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

@Slf4j
public final class GzipUtils {

  /**
   * gzip 压缩数据.
   */
  public static InputStream gzip(String source) {
    try (ByteArrayOutputStream bos = new ByteArrayOutputStream(
        source.length()); GZIPOutputStream gzip = new GZIPOutputStream(bos)) {
      gzip.write(source.getBytes("UTF-8"));
      gzip.close();
      byte[] compressed = bos.toByteArray();
      bos.close();
      return new ByteArrayInputStream(compressed);
    } catch (Throwable e) {
      log.error(e.getMessage(), e);
      ExceptionUtils.wrapAndThrow(e);
    }
    return null;
  }
}
