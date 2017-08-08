package com.rit.projKPT;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class MapFreq {
	int Frequency;
	HashSet<Integer> tw;
	Map<Integer, Integer> termFreq;

	public MapFreq(HashSet<Integer> hs) {
		Frequency = 0;
		tw = hs;
		termFreq = new HashMap<>();

	}

	@Override
	public String toString() {
		String MapFreq = Frequency + ":\t" + tw;

		return MapFreq;
	}
}
