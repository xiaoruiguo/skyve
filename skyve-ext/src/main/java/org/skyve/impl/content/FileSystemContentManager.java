package org.skyve.impl.content;

import java.io.File;

import org.skyve.content.AttachmentContent;
import org.skyve.content.BeanContent;
import org.skyve.content.ContentIterable;
import org.skyve.content.SearchResult;
import org.skyve.content.SearchResults;
import org.skyve.impl.util.UtilImpl;
import org.skyve.util.FileUtil;

/**
 * A content manager class that implements a file system storage but can't be searched or iterated.
 */
public class FileSystemContentManager extends AbstractContentManager {
	@Override
	public void put(BeanContent content) throws Exception {
		// nothing to do here
	}

	@Override
	public void put(AttachmentContent attachment, boolean index) throws Exception {
		if (UtilImpl.CONTENT_FILE_STORAGE) {
			StringBuilder absoluteContentStoreFolderPath = new StringBuilder(128);
			absoluteContentStoreFolderPath.append(UtilImpl.CONTENT_DIRECTORY).append(FILE_STORE_NAME).append('/');
			byte[] content = attachment.getContentBytes();
			writeContentFiles(absoluteContentStoreFolderPath, attachment, content);
		}
	}

	@Override
	public AttachmentContent get(String contentId) throws Exception {
		if (UtilImpl.CONTENT_FILE_STORAGE) {
			StringBuilder absoluteContentStoreFolderPath = new StringBuilder(128);
			absoluteContentStoreFolderPath.append(UtilImpl.CONTENT_DIRECTORY).append(FILE_STORE_NAME).append('/');
			return getFromFileSystem(absoluteContentStoreFolderPath, contentId);
		}
		return null;
	}

	@Override
	public void remove(BeanContent content) throws Exception {
		// nothing to do here
	}

	@Override
	public void remove(String contentId) throws Exception {
		if (UtilImpl.CONTENT_FILE_STORAGE) {
			StringBuilder path = new StringBuilder(128);
			path.append(UtilImpl.CONTENT_DIRECTORY).append(FILE_STORE_NAME).append('/');
			AbstractContentManager.appendBalancedFolderPathFromContentId(contentId, path, false);
			File dir = new File(path.toString());
			File thirdDir = null;
			if (dir.exists()) {
				thirdDir = dir.getParentFile();
				FileUtil.delete(dir);
			}
			
			// Delete the folder structure housing the content file, if empty.
			if ((thirdDir != null) && thirdDir.exists() && thirdDir.isDirectory()) {
				File secondDir = thirdDir.getParentFile();
				if (thirdDir.delete()) {
					if ((secondDir != null) && secondDir.exists() && secondDir.isDirectory()) {
						File firstDir = secondDir.getParentFile();
						if (secondDir.delete()) {
							if ((firstDir != null) && firstDir.exists() && firstDir.isDirectory()) {
								firstDir.delete();
							}
						}
					}
				}
			}
		}
	}

	@Override
	public SearchResults google(String search, int maxResults) throws Exception {
		return new SearchResults();
	}

	@Override
	public void truncate(String customerName) throws Exception {
		// do nothing
	}

	@Override
	public void truncateAttachments(String customerName) throws Exception {
		// do nothing
	}

	@Override
	public void truncateBeans(String customerName) throws Exception {
		// do nothing
	}

	@Override
	public ContentIterable all() throws Exception {
		return new ContentIterable() {
			@Override
			public ContentIterator iterator() {
				return new ContentIterator() {
					@Override
					public SearchResult next() {
						return null;
					}
					
					@Override
					public boolean hasNext() {
						return false;
					}
					
					@Override
					public long getTotalHits() {
						return 0;
					}
				};
			}
		};
	}

	@Override
	public void close() throws Exception {
		// do nothing
	}

	@Override
	public void init() throws Exception {
		// do nothing
	}

	@Override
	public void dispose() throws Exception {
		// do nothing
	}

	@Override
	public void reindex(AttachmentContent attachment, boolean index) throws Exception {
		// nothing to do here
	}
}
