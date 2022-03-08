package com.bingoabin.utils;


import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;
import com.hankcs.hanlp.collection.trie.DoubleArrayTrie;
import com.hankcs.hanlp.collection.trie.bintrie.BinTrie;
import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.dictionary.CoreDictionary;
import com.hankcs.hanlp.dictionary.DynamicCustomDictionary;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhengqi03
 * @date 20210727
 */
public class MultiTenantCustomDictionary {

    /**
     * Hanlp分业务线处定义词库
     */
    private final static Map<Long, DynamicCustomDictionary> multiTenantCustomDictionary = new ConcurrentHashMap<>();

    private final static Map<Long, Segment> multiTenantCustomSegment = new ConcurrentHashMap<>();

    /**
     * 词根中文到英文的倒排索引
     */
    private final static Map<Long, Map<String, Set<String>>> lemmaReverseIndex = new HashMap<>();


    private static void preHandle(Long groupId) {
        if (!multiTenantCustomDictionary.containsKey(groupId)) {
            DynamicCustomDictionary dynamicCustomDictionary = new DynamicCustomDictionary();
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(ResourceUtils.getFile("classpath:data/dictionary/CustomNewDictionary.txt"))))) {
                String line;
                while((line = reader.readLine()) != null) {
                    dynamicCustomDictionary.add(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            multiTenantCustomDictionary.put(groupId, dynamicCustomDictionary);
            Segment segment = HanLP.newSegment().enableCustomDictionary(dynamicCustomDictionary);
            multiTenantCustomSegment.put(groupId, segment);
            lemmaReverseIndex.put(groupId, new HashMap<>());
        }
    }

    public static boolean loadMainDictionary(String mainPath, String path[], DoubleArrayTrie<CoreDictionary.Attribute> dat, boolean isCache) {
        return DynamicCustomDictionary.loadMainDictionary(mainPath, path, dat, isCache);
    }


    /**
     * 加载用户词典（追加）
     *
     * @param path                  词典路径
     * @param defaultNature         默认词性
     * @param customNatureCollector 收集用户词性
     * @return
     */
    public static boolean load(String path, Nature defaultNature, TreeMap<String, CoreDictionary.Attribute> map, LinkedHashSet<Nature> customNatureCollector) {
        return DynamicCustomDictionary.load(path, defaultNature, map, customNatureCollector);
    }


    /**
     * 往自定义词典中插入一个新词（非覆盖模式）<br>
     * 动态增删不会持久化到词典文件
     *
     * @param groupId             业务线ID
     * @param word                新词 如“裸婚”
     * @param natureWithFrequency 词性和其对应的频次，比如“nz 1 v 2”，null时表示“nz 1”
     * @return 是否插入成功（失败的原因可能是不覆盖、natureWithFrequency有问题等，后者可以通过调试模式了解原因）
     */
    public static boolean add(Long groupId, String word, String natureWithFrequency) {
        preHandle(groupId);
        return multiTenantCustomDictionary.get(groupId).add(word, natureWithFrequency);
    }

    /**
     * 往自定义词典中插入一个新词（非覆盖模式）<br>
     * 动态增删不会持久化到词典文件
     *
     * @param word 新词 如“裸婚”
     * @return 是否插入成功（失败的原因可能是不覆盖等，可以通过调试模式了解原因）
     */
    public static boolean add(Long groupId, String word) {
        preHandle(groupId);
        return multiTenantCustomDictionary.get(groupId).add(word);
    }

    /**
     * 以覆盖模式增加新词<br>
     * 动态增删不会持久化到词典文件
     *
     * @param word
     * @return
     */
    public static boolean insert(Long groupId, String word, String code) {
        preHandle(groupId);
        Map<String, Set<String>> groupLemmaMapping = lemmaReverseIndex.get(groupId);
        if (!groupLemmaMapping.containsKey(word)) {
            groupLemmaMapping.put(word, new HashSet<>());
        }
        groupLemmaMapping.get(word).add(code);
        return multiTenantCustomDictionary.get(groupId).insert(word);
    }

    public static boolean loadDat(String path, DoubleArrayTrie<CoreDictionary.Attribute> dat) {
        return DynamicCustomDictionary.loadDat(path, HanLP.Config.CustomDictionaryPath, dat);
    }

    /**
     * 从磁盘加载双数组
     *
     * @param path          主词典路径
     * @param customDicPath 用户词典路径
     * @return
     */
    public static boolean loadDat(Long groupId, String path, String customDicPath[], DoubleArrayTrie<CoreDictionary.Attribute> dat) {
        return DynamicCustomDictionary.loadDat(path, customDicPath, dat);
    }

    /**
     * 查单词
     *
     * @param key
     * @return
     */
    public static CoreDictionary.Attribute get(Long groupId, String key) {
        preHandle(groupId);
        return multiTenantCustomDictionary.get(groupId).get(key);
    }

    /**
     * 删除单词<br>
     * 动态增删不会持久化到词典文件
     *
     * @param key
     */
    public static void remove(Long groupId, String key) {
        preHandle(groupId);
        multiTenantCustomDictionary.get(groupId).remove(key);
    }

    /**
     * 前缀查询
     *
     * @param key
     * @return
     */
    public static LinkedList<Map.Entry<String, CoreDictionary.Attribute>> commonPrefixSearch(Long groupId, String key) {
        preHandle(groupId);
        return multiTenantCustomDictionary.get(groupId).commonPrefixSearch(key);
    }

    /**
     * 前缀查询
     *
     * @param chars
     * @param begin
     * @return
     */
    public static LinkedList<Map.Entry<String, CoreDictionary.Attribute>> commonPrefixSearch(Long groupId, char[] chars, int begin) {
        preHandle(groupId);
        return multiTenantCustomDictionary.get(groupId).commonPrefixSearch(chars, begin);
    }


    /**
     * 词典中是否含有词语
     *
     * @param key 词语
     * @return 是否包含
     */
    public static boolean contains(Long groupId, String key) {
        preHandle(groupId);
        return multiTenantCustomDictionary.get(groupId).contains(key);
    }

    /**
     * 获取词典对应的trie树
     *
     * @return
     * @deprecated 谨慎操作，有可能废弃此接口
     */
    public static BinTrie<CoreDictionary.Attribute> getTrie(Long groupId) {
        preHandle(groupId);
        return multiTenantCustomDictionary.get(groupId).getTrie();
    }

    /**
     * 解析一段文本（目前采用了BinTrie+DAT的混合储存形式，此方法可以统一两个数据结构）
     *
     * @param text      文本
     * @param processor 处理器
     */
    public static void parseText(Long groupId, char[] text, AhoCorasickDoubleArrayTrie.IHit<CoreDictionary.Attribute> processor) {
        preHandle(groupId);
        multiTenantCustomDictionary.get(groupId).parseText(text, processor);
    }

    /**
     * 解析一段文本（目前采用了BinTrie+DAT的混合储存形式，此方法可以统一两个数据结构）
     *
     * @param text      文本
     * @param processor 处理器
     */
    public static void parseText(Long groupId, String text, AhoCorasickDoubleArrayTrie.IHit<CoreDictionary.Attribute> processor) {
        preHandle(groupId);
        multiTenantCustomDictionary.get(groupId).parseText(text, processor);
    }

    /**
     * 最长匹配
     *
     * @param text      文本
     * @param processor 处理器
     */
    public static void parseLongestText(Long groupId, String text, AhoCorasickDoubleArrayTrie.IHit<CoreDictionary.Attribute> processor) {
        preHandle(groupId);
        multiTenantCustomDictionary.get(groupId).parseLongestText(text, processor);
    }

    /**
     * 热更新（重新加载）<br>
     * 集群环境（或其他IOAdapter）需要自行删除缓存文件（路径 = HanLP.Config.CustomDictionaryPath[0] + Predefine.BIN_EXT）
     *
     * @return 是否加载成功
     */
    public static boolean reload(Long groupId) {
        preHandle(groupId);
        return multiTenantCustomDictionary.get(groupId).reload();
    }

    public static List<Term> parseText(Long groupId, String text) {
        preHandle(groupId);
        return multiTenantCustomSegment.get(groupId).seg(text);
    }

    /**
     * 获取自定义词根的中文到英文
     * @param groupId 用户组ID
     * @param lemmaValues
     * @return
     */
    public static Map<String, String> getLemmaReverseIndexByNames(Long groupId, List<String> lemmaValues) {
        Map<String, Set<String>> lemmaReverseIndexByGroup = lemmaReverseIndex.get(groupId);
        Map<String, String> result = new HashMap<>();
        for (String lemmaValue : lemmaValues) {
            if(lemmaReverseIndexByGroup.containsKey(lemmaValue)) {
                result.put(lemmaValue, String.join(",", lemmaReverseIndexByGroup.get(lemmaValue)));
            }
        }
        return result;
    }

    public static void customLemmaInit(List<Triple<Long,String,String>> customLemmas) {
        for (Triple<Long, String, String> customLemma : customLemmas) {
            String[] lemmaNames = customLemma.getRight().split(",");
            for (String lemmaName : lemmaNames) {
                insert(customLemma.getLeft(), lemmaName, customLemma.getMiddle());
            }
        }
    }

    public static void clear() {
        multiTenantCustomDictionary.clear();
        multiTenantCustomSegment.clear();
        lemmaReverseIndex.clear();
    }
}