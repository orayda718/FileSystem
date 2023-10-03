/**
 * Assignment 4 CS1027
 * Due: 4.6.2023
 * Description: This class represents the linked structure that will store the information of the file objects in a file system
 * @author Orayda Shagifa
 */

import java.util.Iterator;
import java.util.ArrayList;

public class FileStructure {
	
	/** a reference to the root node */
	private NLNode<FileObject> root;

	/**
	 * constructor creates the file structure
	 * @param fileObjectName name of file object to be stored in the root node
	 * @throws FileObjectException thrown if there is a problem while constructing the FileObject
	 */
	public FileStructure (String fileObjectName) throws FileObjectException {
		// creates and stores file object in root node
		FileObject file = new FileObject(fileObjectName);
		root = new NLNode<FileObject>(file, null);

		// if the file object is a directory, uses auxiliary recursive algorithm (sends in root)
		if (file.isDirectory()) {
			buildStructure(root);
		}
	}

	/**
	 * auxiliary helper method uses recursive algorithm to  build the file structure 
	 * @param r root node
	 */
	private void buildStructure(NLNode<FileObject> r) {
		// get the iterator from the file object stored in the root
		FileObject f = r.getData();
		Iterator<FileObject> iterator = f.directoryFiles();

		while (iterator.hasNext()) {
			// creates new node using iterator and sets node as the child of r
			NLNode<FileObject> n = new NLNode<FileObject>(iterator.next(), r);
			r.addChild(n);

			// recursive algorithm is invoked if file object stored in the new node n is a directory
			if (n.getData().isDirectory()) buildStructure(n);
		}
	}

	/**
	 * getter returns the root node
	 * @return root
	 */
	public NLNode<FileObject> getRoot() {
		return root;
	}

	/**
	 * returns a String iterator with the names of all the files of the specified type represented by nodes of this FileStructure
	 * @param type specified type
	 * @return s iterator 
	 */
	public Iterator<String> filesOfType(String type) {
		// uses auxiliary recursive algorithm and converts arrayList to iterator
		Iterator<String> s = stringIterator(root, type).iterator();
		
		return s;
	}

	/**
	 * auxiliary helper method uses recursive algorithm to create an ArrayList containing the names of the files of the specified type
	 * @param r root
	 * @param type specified type
	 * @return s ArrayList containing file names
	 */
	private ArrayList<String> stringIterator (NLNode<FileObject> r, String type) {
		// creates String ArrayList to store file names
		ArrayList<String> s = new ArrayList<String>();
		
		// if the root is a file, name (absolute path) is added to the ArrayList
		if (r.getData().isFile()) {
			if (r.getData().getLongName().endsWith(type)) s.add(r.getData().getLongName());
		}
		
		else {
			// get iterator from root storing file objects directly stored in r
			Iterator<NLNode<FileObject>> iterator = r.getChildren();
			
			while (iterator.hasNext()) {
				// invokes recursive algorithm for each file object in iterator and adds result to the ArrayList
				s.addAll(stringIterator(iterator.next(), type)) ;
			}
		}
		
		// returns the ArrayList
		return s;
	}

	/**
	 * looks for a file with the specified name inside this FileStructure
	 * @param name specified name
	 * @return absolute path or "" if the file is not found
	 */
	public String findFile(String name) {
		// uses auxiliary recursive algorithm
		return fileFinder(root, name);
	}
	
	/**
	 * auxiliary helper method uses recursive algorithm to find the file of the specified type
	 * @param r root
	 * @param name specified name
	 * @return absolute path or "" if the file is not found
	 */
	private String fileFinder(NLNode<FileObject> r, String name) {
		// if the root is a file and it has the specified name, it's absolute path is returned
		if (r.getData().isFile()) {
			if (r.getData().getName().equals(name)) return r.getData().getLongName();
		}

		// get iterator from root storing file objects directly stored in r
		Iterator<NLNode<FileObject>> iterator = r.getChildren();
		
		while (iterator.hasNext()) {
			// invokes recursive algorithm for each file object
			String result = fileFinder(iterator.next(), name);
			
			// returns the result (absolute path) if the string isn't empty
			if (!result.equals("")) return result;
		}

		// returns empty string if file is not found
		return "";
	}

}
