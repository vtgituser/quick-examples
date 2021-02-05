package com.vt;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Iterator;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.*;
import org.bouncycastle.openpgp.jcajce.JcaPGPObjectFactory;
import org.bouncycastle.openpgp.operator.bc.BcPGPDataEncryptorBuilder;
import org.bouncycastle.openpgp.operator.bc.BcPublicKeyKeyEncryptionMethodGenerator;
import org.bouncycastle.openpgp.operator.jcajce.JcaKeyFingerprintCalculator;
import org.bouncycastle.openpgp.operator.jcajce.JcePBESecretKeyDecryptorBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePublicKeyDataDecryptorFactoryBuilder;

public class PGPUtil {
  private static final int BUFFER_SIZE = 8192;

  public static InputStream newInputStream(Path path) throws IOException, PGPException {

    InputStream sourceInputStream = null;
    InputStream pgpDecryptSourceInputStream = null;
    InputStream pgpDecompressSourceInputStream = null;
    InputStream pgpLiteralSourceInputStream = null;
    InputStream privateKeyInputStream = null;

    try {
      String privateKey =
          "lQPFBFw9cfYBCADXhAoOGA6d9YMI7txXiRgWv03wMYLXf3CjJCJ01Z1mQ5G7KUmG\n"
              + "gwDii6vDMrVZoLwwrs4cCV9I6MCR5OahikF4EZq3Pma2e0//B/V5jkT6FE7zjZ8V\n"
              + "c86ZX4Ir3iFXLwWUDcdik8O5sxnh3DY/H3tqTjxI3PUqiJvJ0cc0nuhUUiZWxXCk\n"
              + "hnEB++oLqCLvpA/9jch+IilW56v2GfVW2XIHQl90T5W/l1KLx1AoOEfqZEBofxdj\n"
              + "6Ymm43xCBUVLt2+yHE2+UYXIyKtLjQkp9TpAsyMkkBW5a/iXmUA3DLNy4rU2vfue\n"
              + "jwuVK3pzv+6mzPwAx5sICz9H3fnqk6OD+pEDABEBAAH+BwMCIsdfW4di5Uzk7gG9\n"
              + "a7C0cjf/7mOp0WKMLqE+5l2yXaW6NOyamJnaHxsMaR4RgVKaIORl+HZicXrUEx/r\n"
              + "BEy38XCCR84WoV9+r+J/dsBh+ISN2E8KfLRXUUoonQAll9azsDvCoiGJCJAXBUOd\n"
              + "04oAVQ+urKvO4IzgwBj6rcJstp2ZqcAtqamqJTAi2sUYBcjIhKZQhVFe+JGihFLS\n"
              + "Llmw3bhQ3YBEkenaZgNy8HicZdPzWFEMYdbLfBH5TUvvcLAK1aam9DlCnS6U/MXa\n"
              + "DcIKHvgDTQknXGMP8C2ZirfrCLPurmj1Kuk8UH0SSP51lgPuCyN0Vtjo17lbL4Wf\n"
              + "GCkACpoOCFIVq4gbXzMDlHuDHwlVPu6517yaamIn3nBr4JXg/9lxvOzJSwWjP1yz\n"
              + "8T9xMROQ25OWoGp5tHnDiR1usiFl1WAEhWdiS2AEQByTZs7Qz+ISTLf0cJvGaLTb\n"
              + "BF23T60UgG03cU2B6ROug0O8q6p0paiCPnkzypU8Fgq6KgZoxu/YmbMXageg+Bj3\n"
              + "gVd4llF4utub/4aOCFTotZyYaGrFwEqa5a32a0lRdMSx2H4+ogcesDG4I8yNszXW\n"
              + "yC7kucrIOyPFyV83TFovBmNvNDUqBPwrqAnY2njGQJN7IjY1wkSCxemH+tQDYtxs\n"
              + "Rv12SXAYO6lJQz+AlUAfNswDr4VkMqBSzqF7nwpsjnsaN30uORZM8wCYki0+1ZW9\n"
              + "S2V9uPsTKeI4N9OKJ3RGZvflx0PhRno4ZMcU5UPRfaSLBnAXtAhsdpEaoeuOm8nn\n"
              + "L6tJo3c6EutcCzuYzHlyUkuU47ixFIUSBGbWmWWgyDtIbq8jPhQ0SLTnz7NUvVWP\n"
              + "5aUVUdbvify/3nxsI+CYLg+emoD4Y1+lhWUUkZjyKXpySS8ebc8YfPQ0gJgBj0Ut\n"
              + "AQEqH04hgA+0HG5lbW90ZXN0IDxuZW1vdGVzdEBhZXhwLmNvbT6JAVQEEwEIAD4W\n"
              + "IQRw3Aicdo+tgYUGsrwYz9gF/F3oEwUCXD1x9gIbAwUJA8JnAAULCQgHAgYVCgkI\n"
              + "CwIEFgIDAQIeAQIXgAAKCRAYz9gF/F3oE3RoB/4zT8uaBQDwhEDKnkDeF7QsfFOk\n"
              + "lVrKw9eR2d8k5FsBPnZjHJW4IMx6Kei64kxWj4/cmPEi8mwIEMQF9comIO0ctAIl\n"
              + "a8utzWKYhnHpeiiC41kFDClc4wW8X0YTBlF8/oBN7FpGcorbTPhC0iL6Dq1Eq4dY\n"
              + "jkeJSVsRfG7FL25Iy7WGjDnC6vcgMK5E/gMOcOfOUjxNF/qsKAW616lybiSxOe20\n"
              + "mbWpUi3wc2tR/uTkt4hBppYqd+jBzxeV4jADuGTuXThOSD1sZwE7SSBvDsmwpj9R\n"
              + "QxoYGnT1kc5mK9gKh7H077i/hYV/4qgYsMMuJA+WNexf7iZ9xOiErggwtv01nQPG\n"
              + "BFw9cfYBCADA014kH/MjAUwUKGYjAUaqRyfzCA1lWStDZdy04w0ZGvAyO+dvDqOf\n"
              + "oeKy25Tfnf5p/tfuOysAwyhA+DztW5a/9YRZrYK6dWcHcumWQr+pBIiAjaaAvpJ+\n"
              + "EjFnZmhO5GPm301EIOAhpy+IE2cb/teCkbUY4yksCPAOoHDyjAaCxTBEti+qe5fi\n"
              + "RTWAdK0fg3wLKEXhI1p5uZY736CD0CL7ZKsSZjgqJkEcGE1u0yl7PmbbrdBKuqqT\n"
              + "LXf+yx9IjXP67iJdV/ieg9atVU75FgHRR0vY4CE12GhqwtULx2RFYcDMsgAU6LqO\n"
              + "B1kFCEGpNrXBjMqGNOh8DGOrqzwj6s7fABEBAAH+BwMCkpFZJBPoyi7k9NNqjVP8\n"
              + "MaDjayhO7DgZTYAufE0PSJXmHim+VT59UuExRQdLqbYFE9mQUn6Mb+DcA6qrGwoq\n"
              + "/gjISTRRE+9Ln1iLh8imWx0yDgdnWcdG7C7QYxu/rmz/+5empvuUSOICpJ+gFCzR\n"
              + "goXYTByx3q/fGIVFDDTMuwWx0NpaCdUPpmJIuB23BRNmdfPEULNGqOE5/mNlDlv7\n"
              + "FBx5hAmGvmen/AHTy2ByWmSQFxvZ30OZnMAxmp6jfsLXr7Hb8azW3lBcPfA0rX9o\n"
              + "KKcU6CLX9JPU9u/2UAcHnuTnAk1/SykR9Ax2+F5Rvp7A+eYOicqmMwF0N1kksX3c\n"
              + "YZV0POlguq02bnr76IfNHi6rlMqfksLa4pjdki64TUjqrdjGs6+3MawTbQxo0zyB\n"
              + "9som18FnRGoKQxNw5yv50GY6ufscMwvrjj61ZnhgoiRJllmwUCHzPTeSb6DtYcdJ\n"
              + "WPKsAh7YEB3uKIucyvvMqv3Z1weGLDyaz8erZBY7c/UgBtE/53GkeD4q176tVcqN\n"
              + "+gSYdnDpOSZyz/d+xviK0V7UgSelRJrO0gnQMc3enqEUPhpQNh3peL00aRKEx4M7\n"
              + "yp/HB2S9o2KDaN2kCFjJzwuxvH2TU6E4XQpbcD4jVrohwuA+wUjeqstX65rroxiy\n"
              + "KbB9t5w1hWjU53pWfNgobSis0g57v/yPNOx8wz1GTCy4mIl9ShtUlFHmnKsSXY7L\n"
              + "KFWrG4fbvUiixHwXiwDkrWqb7pFA3DeApIa+amDnRAjlNFQFwRUyhyn0jwy2Pd5f\n"
              + "9labmN1PLQzRcjiRkLrD19wRYWWFL9LzvlIOWhhhvZ/ZbPS+i/7iW00IqD1EeG/5\n"
              + "4wnlL+KCeLFSHvAt/mTn8JEUSv/2jcHVt8/9S20hxjIomp+0tu+mUPRzC5dN5EUI\n"
              + "FUZ+2Pq3iQE8BBgBCAAmFiEEcNwInHaPrYGFBrK8GM/YBfxd6BMFAlw9cfYCGwwF\n"
              + "CQPCZwAACgkQGM/YBfxd6BP9SAf+OJ4uoJt6IoXPNzhNrMDA44UY/7aIsvVst+Xs\n"
              + "dgOI78FcUFW+/IJ2AIp5y9hpn83zUnP8zj6UuUKHBNfafiBnhVlkS324Kz4/PEFj\n"
              + "mIAjD1wl5NEoG6zIpvDYllNmr7poIyIiWKopQkXWsUcg9LPYT/twxzWSq/PjIc2V\n"
              + "DH2BzSyho6J1qUnp/zojE5fFMw5C6XWKm3E0ZDeShivlSciFsCZq7p8o58d3V6aI\n"
              + "fLsDFapSfPErv18PfWXZVGm5yVWtm8fIVSbSZ2FkACgk3bLCFemaSao6e0gtONBC\n"
              + "AtLWBA2UNGEihRE8FZ3skJWgNQizt4h/47G83El4NwIbZdew+A==\n"
              + "=N5Gw";
      String decPwd = "nemotest123";
      final InputStream finalSourceInputStream =
          sourceInputStream =
              org.bouncycastle.openpgp.PGPUtil.getDecoderStream(Files.newInputStream(path));
      privateKeyInputStream =
          org.bouncycastle.openpgp.PGPUtil.getDecoderStream(
              new ByteArrayInputStream(privateKey.getBytes()));

      final JcaPGPObjectFactory pgpObjectFactory = new JcaPGPObjectFactory(sourceInputStream);
      final Object firstPGPObject = pgpObjectFactory.nextObject();
      final PGPEncryptedDataList encryptedData =
          firstPGPObject instanceof PGPEncryptedDataList
              ? (PGPEncryptedDataList) firstPGPObject
              : (PGPEncryptedDataList) pgpObjectFactory.nextObject();

      final Iterator encryptedDataObjects = encryptedData.getEncryptedDataObjects();
      final PGPSecretKeyRingCollection pgpSecretKeyRings =
          new PGPSecretKeyRingCollection(privateKeyInputStream, new JcaKeyFingerprintCalculator());
      PGPPrivateKey pgpPrivateKey = null;
      PGPPublicKeyEncryptedData pgpPublicKeyEncryptedData = null;

      while (pgpPrivateKey == null && encryptedDataObjects.hasNext()) {
        pgpPublicKeyEncryptedData = (PGPPublicKeyEncryptedData) encryptedDataObjects.next();
        final PGPSecretKey pgpSecretKey =
            pgpSecretKeyRings.getSecretKey(pgpPublicKeyEncryptedData.getKeyID());
        pgpPrivateKey =
            pgpSecretKey != null
                ? pgpSecretKey.extractPrivateKey(
                    new JcePBESecretKeyDecryptorBuilder()
                        .setProvider(new BouncyCastleProvider())
                        .build(decPwd.toCharArray()))
                : null;
      }

      if (pgpPrivateKey == null) {
        throw new IllegalStateException("PGP private key not found");
      }

      final InputStream finalPgpDecryptSourceInputStream =
          pgpDecryptSourceInputStream =
              pgpPublicKeyEncryptedData.getDataStream(
                  new JcePublicKeyDataDecryptorFactoryBuilder()
                      .setProvider(new BouncyCastleProvider())
                      .build(pgpPrivateKey));

      final JcaPGPObjectFactory compressedPGPObjectFactory =
          new JcaPGPObjectFactory(pgpDecryptSourceInputStream);
      final PGPCompressedData pgpCompressedData =
          (PGPCompressedData) compressedPGPObjectFactory.nextObject();
      final InputStream finalPgpDecompressSourceInputStream =
          pgpDecompressSourceInputStream = pgpCompressedData.getDataStream();
      final JcaPGPObjectFactory pgpDecompressedObjectFactory =
          new JcaPGPObjectFactory(pgpDecompressSourceInputStream);

      final Object message = pgpDecompressedObjectFactory.nextObject();
      final PGPLiteralData pgpLiteralData =
          message instanceof PGPLiteralData ? (PGPLiteralData) message : null;

      if (pgpLiteralData != null) {
        final InputStream finalPgpLiteralSourceInputStream =
            pgpLiteralSourceInputStream = pgpLiteralData.getInputStream();
        return new BufferedInputStream(pgpLiteralSourceInputStream) {
          @Override
          public void close() {}
        };
      }

      throw new IllegalStateException("No data found in PGP input stream");

    } catch (Exception exc) {
      throw exc;
    }
  }

  public static void main(String[] args) throws Exception {
    Path path = Paths.get("src/main/resources/test1enc.txt");
    Files.deleteIfExists(path);
    Path path2 = Paths.get("src/main/resources/test1decrypted.txt");
    Files.deleteIfExists(path2);
    System.out.println("path:" + path.toUri());
    OutputStream outputStream = PGPUtil.newOutputStream(path);
    InputStream inputStream = Files.newInputStream(Paths.get("src/main/resources/test1.txt"));
    while (true) {
      byte[] b = new byte[BUFFER_SIZE];
      if (inputStream.read(b) == -1) break;
      outputStream.write(b);
    }
    outputStream.flush();
    inputStream.close();
    outputStream.close();
    // Files.copy(newInputStream(path), path2);
    /*        KeyPair keyPair = JavaPGP.generateKeyPair();
    System.out.println(keyPair.getPrivate().toString());
    System.out.println(keyPair.getPublic().toString());*/

  }

  public static OutputStream newOutputStream(Path path) throws PGPException, IOException {

    String publicKey =
        "mQENBFw9cfYBCADXhAoOGA6d9YMI7txXiRgWv03wMYLXf3CjJCJ01Z1mQ5G7KUmG\n"
            + "gwDii6vDMrVZoLwwrs4cCV9I6MCR5OahikF4EZq3Pma2e0//B/V5jkT6FE7zjZ8V\n"
            + "c86ZX4Ir3iFXLwWUDcdik8O5sxnh3DY/H3tqTjxI3PUqiJvJ0cc0nuhUUiZWxXCk\n"
            + "hnEB++oLqCLvpA/9jch+IilW56v2GfVW2XIHQl90T5W/l1KLx1AoOEfqZEBofxdj\n"
            + "6Ymm43xCBUVLt2+yHE2+UYXIyKtLjQkp9TpAsyMkkBW5a/iXmUA3DLNy4rU2vfue\n"
            + "jwuVK3pzv+6mzPwAx5sICz9H3fnqk6OD+pEDABEBAAG0HG5lbW90ZXN0IDxuZW1v\n"
            + "dGVzdEBhZXhwLmNvbT6JAVQEEwEIAD4WIQRw3Aicdo+tgYUGsrwYz9gF/F3oEwUC\n"
            + "XD1x9gIbAwUJA8JnAAULCQgHAgYVCgkICwIEFgIDAQIeAQIXgAAKCRAYz9gF/F3o\n"
            + "E3RoB/4zT8uaBQDwhEDKnkDeF7QsfFOklVrKw9eR2d8k5FsBPnZjHJW4IMx6Kei6\n"
            + "4kxWj4/cmPEi8mwIEMQF9comIO0ctAIla8utzWKYhnHpeiiC41kFDClc4wW8X0YT\n"
            + "BlF8/oBN7FpGcorbTPhC0iL6Dq1Eq4dYjkeJSVsRfG7FL25Iy7WGjDnC6vcgMK5E\n"
            + "/gMOcOfOUjxNF/qsKAW616lybiSxOe20mbWpUi3wc2tR/uTkt4hBppYqd+jBzxeV\n"
            + "4jADuGTuXThOSD1sZwE7SSBvDsmwpj9RQxoYGnT1kc5mK9gKh7H077i/hYV/4qgY\n"
            + "sMMuJA+WNexf7iZ9xOiErggwtv01uQENBFw9cfYBCADA014kH/MjAUwUKGYjAUaq\n"
            + "RyfzCA1lWStDZdy04w0ZGvAyO+dvDqOfoeKy25Tfnf5p/tfuOysAwyhA+DztW5a/\n"
            + "9YRZrYK6dWcHcumWQr+pBIiAjaaAvpJ+EjFnZmhO5GPm301EIOAhpy+IE2cb/teC\n"
            + "kbUY4yksCPAOoHDyjAaCxTBEti+qe5fiRTWAdK0fg3wLKEXhI1p5uZY736CD0CL7\n"
            + "ZKsSZjgqJkEcGE1u0yl7PmbbrdBKuqqTLXf+yx9IjXP67iJdV/ieg9atVU75FgHR\n"
            + "R0vY4CE12GhqwtULx2RFYcDMsgAU6LqOB1kFCEGpNrXBjMqGNOh8DGOrqzwj6s7f\n"
            + "ABEBAAGJATwEGAEIACYWIQRw3Aicdo+tgYUGsrwYz9gF/F3oEwUCXD1x9gIbDAUJ\n"
            + "A8JnAAAKCRAYz9gF/F3oE/1IB/44ni6gm3oihc83OE2swMDjhRj/toiy9Wy35ex2\n"
            + "A4jvwVxQVb78gnYAinnL2GmfzfNSc/zOPpS5QocE19p+IGeFWWRLfbgrPj88QWOY\n"
            + "gCMPXCXk0SgbrMim8NiWU2avumgjIiJYqilCRdaxRyD0s9hP+3DHNZKr8+MhzZUM\n"
            + "fYHNLKGjonWpSen/OiMTl8UzDkLpdYqbcTRkN5KGK+VJyIWwJmrunyjnx3dXpoh8\n"
            + "uwMVqlJ88Su/Xw99ZdlUabnJVa2bx8hVJtJnYWQAKCTdssIV6ZpJqjp7SC040EIC\n"
            + "0tYEDZQ0YSKFETwVneyQlaA1CLO3iH/jsbzcSXg3Ahtl17D4\n"
            + "=Ed86";
    InputStream publicKeyInputStream = new ByteArrayInputStream(publicKey.getBytes());
    final PGPPublicKey pgpPublicKey = getPublicKey(publicKeyInputStream);
    final BcPGPDataEncryptorBuilder pgpDataEncryptorBuilder =
        new BcPGPDataEncryptorBuilder(PGPEncryptedData.AES_256);
    pgpDataEncryptorBuilder.setWithIntegrityPacket(false);
    pgpDataEncryptorBuilder.setSecureRandom(new SecureRandom());
    final PGPEncryptedDataGenerator encryptedDataGenerator =
        new PGPEncryptedDataGenerator(pgpDataEncryptorBuilder);
    encryptedDataGenerator.addMethod(new BcPublicKeyKeyEncryptionMethodGenerator(pgpPublicKey));

    final PGPCompressedDataGenerator pgpCompressedDataGenerator =
        new PGPCompressedDataGenerator(PGPCompressedData.ZIP);

    final PGPLiteralDataGenerator pgpLiteralDataGenerator = new PGPLiteralDataGenerator();

    try {
      OutputStream destinationOutputStream = Files.newOutputStream(path);
      OutputStream pgpEncryptOutputStream =
          encryptedDataGenerator.open(destinationOutputStream, new byte[BUFFER_SIZE]);
      OutputStream pgpCompressOutputStream =
          pgpCompressedDataGenerator.open(pgpEncryptOutputStream);
      return pgpLiteralDataGenerator.open(
          pgpCompressOutputStream,
          PGPLiteralData.TEXT,
          PGPLiteralData.CONSOLE,
          new Date(),
          new byte[BUFFER_SIZE]);

    } catch (Exception exc) {
      exc.printStackTrace();
    } finally {
      publicKeyInputStream.close();
    }
    return null;
  }

  private static PGPPublicKey getPublicKey(InputStream in) throws PGPException, IOException {

    PGPPublicKeyRing pkRing;
    PGPPublicKey result = null;
    PGPPublicKey key;

    final PGPPublicKeyRingCollection pgpPublicKeyRings =
        new PGPPublicKeyRingCollection(
            org.bouncycastle.openpgp.PGPUtil.getDecoderStream(in),
            new JcaKeyFingerprintCalculator());

    Iterator keyRings = pgpPublicKeyRings.getKeyRings();

    while (keyRings.hasNext()) {

      pkRing = (PGPPublicKeyRing) keyRings.next();

      Iterator pkIt = pkRing.getPublicKeys();

      while (pkIt.hasNext()) {

        key = (PGPPublicKey) pkIt.next();

        if (key.isEncryptionKey()) {

          result = key;

          break;
        }
      }
    }

    return result;
  }
}
