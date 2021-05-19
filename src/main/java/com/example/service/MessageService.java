package com.example.service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.springframework.stereotype.Service;

@Service
public class MessageService {

	public String getMessage(List<List<String>> messages) {

		Set<Word> treeSet = new TreeSet<>(Comparator.comparing(Word::getPosition));

		for (List<String> list : messages) {
			list.forEach(withCounter((i, text) -> {
				if (!text.isEmpty()) {
					treeSet.add(new Word(i, text));
				}
			}));
		}

		StringBuilder builder = new StringBuilder();
		treeSet.forEach(e -> builder.append(e.getText()).append(" "));
		return builder.toString().trim();
	}

	private <T> Consumer<T> withCounter(BiConsumer<Integer, T> consumer) {
		AtomicInteger counter = new AtomicInteger(0);
		return item -> consumer.accept(counter.getAndIncrement(), item);
	}

	private class Word {

		private int position;
		private String text;

		public Word(int position, String text) {
			this.position = position;
			this.text = text;
		}

		public int getPosition() {
			return position;
		}

		public String getText() {
			return text;
		}

	}

}
