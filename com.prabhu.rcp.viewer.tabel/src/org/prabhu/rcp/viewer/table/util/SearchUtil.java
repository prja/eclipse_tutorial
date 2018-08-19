package org.prabhu.rcp.viewer.table.util;

import java.util.ArrayList;
import java.util.List;

public class SearchUtil {
	
	public static int[] getSearchTermOccurrences(final String searchTerm,
			final String content) {
		if (searchTerm == null || searchTerm.length() == 0) {
			return new int[0];
		}
		if (content == null) {
			throw new IllegalArgumentException("content is null");
		}
		final List<Integer> list = new ArrayList<Integer>();
		int searchTermLength = searchTerm.length();
		int index;
		int fromIndex = 0;
		int lastIndex = -1;
		int lastLength = 0;
		while (true) {
			index = content.indexOf(searchTerm, fromIndex);
			if (index == -1) {
				// no occurrence of "searchTerm" in "content" starting from
				// index "fromIndex"
				if (lastIndex != -1) {
					// but there was a previous occurrence
					list.add(Integer.valueOf(lastIndex));
					list.add(Integer.valueOf(lastLength));
				}
				break;
			}
			if (lastIndex == -1) {
				// the first occurrence of "searchTerm" in "content"
				lastIndex = index;
				lastLength = searchTermLength;
			} else {
				if (lastIndex + lastLength == index) {
					// the current occurrence is right after the previous
					// occurrence
					lastLength += searchTermLength;
				} else {
					// there is at least one character between the current
					// occurrence and the previous one
					list.add(Integer.valueOf(lastIndex));
					list.add(Integer.valueOf(lastLength));
					lastIndex = index;
					lastLength = searchTermLength;
				}
			}
			fromIndex = index + searchTermLength;
		}
		final int n = list.size();
		final int[] result = new int[n];
		for (int i = 0; i != n; i++) {
			result[i] = list.get(i);
		}
		return result;
	}

}
