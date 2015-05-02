/**
 *  Sensos IoT Platform.
 *  Copyright (C) 2015 sensos
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package sensos.util.misc;

import java.io.*;
import java.util.zip.*;
import java.nio.ByteBuffer;

public class FindClass {
	private static final int BUFFER_SIZE = 4096;
	private static final String[] JAR_EXTENSIONS = { ".zip", ".jar", ".war",
			".ear", ".rar", };

	public static void findClass(String path, String classToLookFor,
			boolean recursive, boolean reportVersion) throws Exception {
		if (path == null) {
			return;
		}

		// normalize class name
		if (!classToLookFor.contains("/")) {
			classToLookFor = classToLookFor.replace('.', '/');
			if (classToLookFor.endsWith("/class")) {
				classToLookFor = classToLookFor.substring(0,
						classToLookFor.length() - 6)
						+ ".class";
			}
		}

		// normalize path, change \ to /
		path = path.replace(File.separatorChar, '/');
		File f = new File(path);
		if (f.isDirectory()) {
			findClassInDirectory(path, classToLookFor, recursive, reportVersion);
		} else if (f.isFile() && hasJarExtension(path)) {
			ZipInputStream in = new ZipInputStream(new FileInputStream(path));
			findClassInJar(in, classToLookFor, path, recursive, reportVersion);
			in.close();
		} else if (f.isFile() && path.contains(classToLookFor)) {
			if (reportVersion) {
				InputStream in = new FileInputStream(f);
				String classVersion = getClassVersion(in);
				in.close();
				path += " (" + classVersion + ")";
			}
			showPath(path);
		}
	}

	private static String getClassVersion(InputStream in) throws IOException {
		byte[] b = new byte[8];
		in.read(b);

		ByteBuffer bb = (ByteBuffer) ByteBuffer.allocate(8).put(b).rewind();

		int magic = bb.getInt();
		if (magic != 0) { // not a Java class file
			return "not a Java class";
		}

		return String.format("%2$d.%1$d", bb.getShort(), bb.getShort());
	}

	private static void findClassInJar(ZipInputStream in,
			String classToLookFor, String path, boolean recursive,
			boolean reportVersion) throws Exception {
		ZipEntry next = in.getNextEntry();
		while (next != null) {
			if (!next.isDirectory()) {
				if (next.getName().contains(classToLookFor)) {
					if (reportVersion) {
						String classVersion = getClassVersion(in);
						showPath(path + ": " + next.getName() + " ("
								+ classVersion + ")");
					} else {
						showPath(path + ": " + next.getName());
					}
				} else if (recursive && hasJarExtension(next.getName())) {
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					byte[] buffer = new byte[BUFFER_SIZE];
					int n = in.read(buffer, 0, BUFFER_SIZE);
					while (n >= 0) { // read returns -1 if end of current entry
										// is reached.
						out.write(buffer, 0, n);
						n = in.read(buffer, 0, BUFFER_SIZE);
					}

					ZipInputStream in2 = new ZipInputStream(
							new ByteArrayInputStream(out.toByteArray()));
					findClassInJar(in2, classToLookFor,
							path + "/" + next.getName(), recursive,
							reportVersion);
					in2.close();
				}
			}
			next = in.getNextEntry();
		}
	}

	private static void findClassInDirectory(String dir, String classToLookFor,
			boolean recursive, boolean reportVersion) throws Exception {
		File d = new File(dir);
		String[] files = d.list();
		for (int i = 0; i < files.length; i++) {
			String filePath = dir + "/" + files[i];
			File file = new File(filePath);
			if (file.isFile() && filePath.contains(classToLookFor)) {
				int idx = filePath.indexOf(classToLookFor);
				if (reportVersion) {
					InputStream in = new FileInputStream(file);
					String classVersion = getClassVersion(in);
					in.close();
					showPath(filePath.substring(0, idx) + ": "
							+ filePath.substring(idx) + " (" + classVersion
							+ ")");
				} else {
					showPath(filePath.substring(0, idx) + ": "
							+ filePath.substring(idx));
				}
			} else if (recursive) {
				if (file.isDirectory()) {
					findClassInDirectory(filePath, classToLookFor, recursive,
							reportVersion);
				} else if (hasJarExtension(filePath)) {
					ZipInputStream in = new ZipInputStream(new FileInputStream(
							file));
					findClassInJar(in, classToLookFor, filePath, recursive,
							reportVersion);
					in.close();
				}
			}
		}
	}

	private static boolean hasJarExtension(String fileName) {
		fileName = fileName.toLowerCase();
		for (int i = 0; i < JAR_EXTENSIONS.length; i++) {
			if (fileName.endsWith(JAR_EXTENSIONS[i])) {
				return true;
			}
		}
		return false;
	}

	private static void showPath(String p) {
		System.out.println(p);
	}

	public static void main(String[] args) throws Exception {
		if (args.length < 2) {
			System.out
					.println("Usage: java FindClass [-v] <jarFileName> <classToLookFor>");
			System.exit(1);
		}

		boolean reportVersion = args[0].equals("-v");
		String path = reportVersion ? args[1] : args[0];
		String classToLookFor = reportVersion ? args[2] : args[1];
		findClass(path, classToLookFor, true, reportVersion);
	}
}
