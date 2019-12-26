package br.com.contmatic.repository.mongodb;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;
import org.joda.time.LocalDate;

public class MongoLeitorDados<T> {

	public Object instanciador(Document bson, Class<?> classe) {
		Object objeto = newer(classe);
		Set<Method> setters = trazMetodosSetters(classe);
		for (Method setter : setters) {
			for (String campo : bson.keySet()) {
				if (setter.getName().toUpperCase().contains(campo.toUpperCase())) {
					setador(setter, objeto, bson, campo, classe);
				}
			}
		}
		return objeto;
	}

	private Class<?> getClasseOutraCollection(String campo, Class<?> classe) {
		String nomeClasseComTipoGenerico = "";
		try {
			nomeClasseComTipoGenerico = classe.getDeclaredField(campo).getGenericType().getTypeName();
		} catch (NoSuchFieldException | SecurityException e) {
			// add logger
			e.printStackTrace();
		}
		int i = 1;
		while (nomeClasseComTipoGenerico.charAt(i) != '<') {
			i++;
		}
		i++;
		int inicio = i;
		while (nomeClasseComTipoGenerico.charAt(i) != '<' && nomeClasseComTipoGenerico.charAt(i) != '>') {
			i++;
		}
		int fim = i;
		try {
			return Class.forName(nomeClasseComTipoGenerico.substring(inicio, fim));
		} catch (ClassNotFoundException e) {
			// add logger
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	private Collection newerCollection(Class<?> classe) {
		if (classe.equals(Set.class)) {
			return new HashSet();
		}
		if (classe.equals(List.class)) {
			return new ArrayList();
		}
		return null;
	}

	private void setador(Method setter, Object objeto, Document bson, String campo, Class<?> classe) {
		Class<?> classeCampo = getCampoClass(campo, classe);
		if (classeCampo.isEnum()) {
			try {
				setter.invoke(objeto, classeCampo.getSuperclass().getMethod("valueOf", Class.class, String.class)
						.invoke(null, classeCampo, bson.get(campo, String.class)));
			} catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException e) {
				// add logger
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// add logger
				return;
			}
			return;
		}
		if (bson.get(campo) instanceof Collection) {
			try {
				setter.invoke(objeto, setadorElementosCollection(bson, campo, classeCampo, classe));
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// add logger
				e.printStackTrace();
			}
			return;
		}
		if (bson.get(campo) instanceof Document) {
			try {
				setter.invoke(objeto, instanciador((Document) bson.get(campo), classeCampo));
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// add logger
				e.printStackTrace();
			}
			return;
		}
		if (classeCampo.equals(LocalDate.class)) {
			try {
				setter.invoke(objeto, LocalDate.parse(bson.get(campo, String.class)));
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// add logger
				e.printStackTrace();
			}
			return;
		}
		try {
			setter.invoke(objeto, bson.get(campo, classeCampo));
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// add logger
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Collection setadorElementosCollection(Object maybeBson, String campo, Class<?> classeCampo,
			Class<?> classe) {
		Class<?> classeTipoGenerico = getTipoGenericoCollection(campo, classe);
		Collection valores = new ArrayList();
		if (maybeBson instanceof Document) {
			Document bson = (Document) maybeBson;
			valores.addAll((Collection) bson.getList(campo, Object.class));
		} else {
			valores.addAll((Collection) maybeBson);
		}
		Collection conjunto = newerCollection(classeCampo);
		for (Object valor : valores) {
			if (valor instanceof Collection) {
				conjunto.add(setadorElementosCollection(valor, campo, getClasseOutraCollection(campo, classe), classe));
				continue;
			}
			if (valor instanceof Document) {
				conjunto.add(instanciador((Document) valor, classeTipoGenerico));
				continue;
			}
			if (classeTipoGenerico.isEnum()) {
				try {
					conjunto.add(classeTipoGenerico.getSuperclass().getMethod("valueOf", Class.class, String.class)
							.invoke(null, classeTipoGenerico, valor));
				} catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException
						| SecurityException e) {
					// add logger
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// add logger
					continue;
				}
				continue;
			}
			if (classeTipoGenerico.equals(LocalDate.class)) {
				try {
					conjunto.add(LocalDate.parse(valor.toString()));
				} catch (IllegalArgumentException e) {
					// add logger
					e.printStackTrace();
				}
				continue;
			}
			conjunto.add(classeTipoGenerico.cast(valor));
		}
		return conjunto;
	}

	private Class<?> getTipoGenericoCollection(String campo, Class<?> classe) {
		String nomeClasseComTipoGenerico = "";
		try {
			nomeClasseComTipoGenerico = classe.getDeclaredField(campo).getGenericType().getTypeName();
		} catch (NoSuchFieldException | SecurityException e) {
			// add logger
			e.printStackTrace();
		}
		int i = nomeClasseComTipoGenerico.length() - 1;
		while (nomeClasseComTipoGenerico.charAt(i) == '>') {
			i--;
		}
		int fim = i + 1;
		while (nomeClasseComTipoGenerico.charAt(i) != '<') {
			i--;
		}
		int inicio = i + 1;
		try {
			return Class.forName(nomeClasseComTipoGenerico.substring(inicio, fim));
		} catch (ClassNotFoundException e) {
			// add logger
			e.printStackTrace();
		}
		return null;
	}

	private Object newer(Class<?> classe) {
		try {
			return classe.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| SecurityException | NoSuchMethodException e) {
			// add logger
			e.printStackTrace();
		}
		return null;
	}

	private Class<?> getCampoClass(String campo, Class<?> classe) {
		try {
			return classe.getDeclaredField(campo).getType();
		} catch (NoSuchFieldException | SecurityException e) {
			// add logger
			e.printStackTrace();
			return null;
		}
	}

	private Set<Method> trazMetodosSetters(Class<?> classe) {
		Set<Method> metodosSet = new HashSet<>();
		StringBuilder nomeMetodoSetter = new StringBuilder();
		for (Method metodo : classe.getDeclaredMethods()) {
			for (Field campo : classe.getDeclaredFields()) {
				nomeMetodoSetter.append("set").append(campo.getName());
				if (metodo.getName().equalsIgnoreCase(nomeMetodoSetter.toString())) {
					metodosSet.add(metodo);
				}
				nomeMetodoSetter.setLength(0);
			}
		}
		return metodosSet;
	}

}
