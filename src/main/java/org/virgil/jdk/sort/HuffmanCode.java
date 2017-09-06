package org.virgil.jdk.sort;

import java.util.*;

/**
 * Created by Virgil on 2017/8/28.
 */
class Data {
    private char c = 0;
    private int frequency;

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "Data [c=" + c + ", frequency=" + frequency + "]";
    }
}

class Node {
    private Node leftChild = null;
    private Node rightChild = null;
    private Data data;

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node [leftChild=" + leftChild + ", data=" + data
                + ", rightChild=" + rightChild + "]";
    }
}

public class HuffmanCode {
    private ArrayList<Node> init(String letters) {
        ArrayList<Node> letterList = new ArrayList<Node>();
        Map<Character, Integer> ci = new HashMap<Character, Integer>();
        for (int i = 0; i < letters.length(); i++) {
            Character character = letters.charAt(i);
            if (ci.keySet().contains(character)) {
                Integer oldValue = ci.get(character);
                ci.put(character, oldValue + 1);
            } else {
                ci.put(character, 1);
            }
        }
        Set<Character> keys = ci.keySet();
        for (Character key : keys) {
            Node node = new Node();
            Data data = new Data();
            data.setC(key);
            data.setFrequency(ci.get(key));
            node.setData(data);
            letterList.add(node);
        }
        sort(letterList);
        return letterList;
    }

    public void sort(ArrayList<Node> letterList) {
        int size = letterList.size();
        if (size == 1) {
            return;
        }
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                if (letterList.get(i).getData().getFrequency() < letterList.get(j).getData().getFrequency()) {
                    Node temp = letterList.get(i);
                    letterList.set(i, letterList.get(j));
                    letterList.set(j, temp);
                }
            }
        }
    }

    private Node createTree(ArrayList<Node> letterList) {
        while (letterList.size() != 1) {
            int size = letterList.size();
            Node nodeLeft = letterList.get(size - 1);
            Node nodeRight = letterList.get(size - 2);
            Node parent = new Node();
            parent.setLeftChild(nodeLeft);
            parent.setRightChild(nodeRight);
            Data data = new Data();
            data.setFrequency(nodeLeft.getData().getFrequency() + nodeRight.getData().getFrequency());
            parent.setData(data);
            letterList.set(size - 2, parent);
            letterList.remove(size - 1);
            sort(letterList);
        }
        Node rootNode = letterList.get(0);
        return rootNode;
    }

    private Map<Character, String> getCode(Node rootNode) {
        Map<Character, String> letterCode = new HashMap<Character, String>();
        if (rootNode.getLeftChild() == null && rootNode.getRightChild() == null) {
            letterCode.put(rootNode.getData().getC(), "0");
            return letterCode;
        }
        getLetterCode(rootNode, "", letterCode);
        return letterCode;
    }

    private void getLetterCode(Node rooNode, String suffix, Map<Character, String> letterCode) {
        if (rooNode != null) {
            if (rooNode.getLeftChild() == null && rooNode.getRightChild() == null) {
                Character character = rooNode.getData().getC();
                letterCode.put(character, suffix);
            }
            getLetterCode(rooNode.getLeftChild(), suffix + "0", letterCode);
            getLetterCode(rooNode.getRightChild(), suffix + "1", letterCode);
        }
    }

    private String encode(Map<Character, String> letterCode, String letters) {
        StringBuilder encode = new StringBuilder();
        for (int i = 0; i < letters.length(); i++) {
            Character character = letters.charAt(i);
            encode.append(letterCode.get(character));
        }
        return encode.toString();
    }

    public String decode(EncodeResult decodeResult) {
        StringBuffer decodeStr = new StringBuffer();
        Map<String, Character> decoderMap = getDecoder(decodeResult.getLetterCode());
        Set<String> keys = decoderMap.keySet();
        String encode = decodeResult.getEncode();
        String temp = "";
        int i = 1;
        while (encode.length() > 0) {
            temp = encode.substring(0, i);
            if (keys.contains(temp)) {
                Character character = decoderMap.get(temp);
                decodeStr.append(character);
                encode = encode.substring(i);
                i = 1;
            } else {
                i++;
            }
        }
        return decodeStr.toString();
    }

    private Map<String, Character> getDecoder(Map<Character, String> letterCode) {
        Map<String, Character> decodeMap = new HashMap<String, Character>();
        Set<Character> characters = letterCode.keySet();
        for (Character ch : characters) {
            decodeMap.put(letterCode.get(ch), ch);
        }
        return decodeMap;
    }

    public static void main(String[] args) {
        HuffmanCode hc = new HuffmanCode();
        String str = "ddbddbbaca";
        ArrayList<Node> nodes = hc.init(str.replaceAll(" ", ""));
        for (Node node : nodes) {
            System.out.println(node.toString());
        }
        Node tree = hc.createTree(nodes);
        Map<Character, String> code = hc.getCode(tree);
        String result = hc.encode(code, str);
        System.out.println(result);
        EncodeResult res = new EncodeResult(result, code);
        String decode = hc.decode(res);
        System.out.println(decode);
    }


}
