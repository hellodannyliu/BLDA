package Common1;
/*
 *    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program; if not, write to the Free Software
 *    Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

/*
 *    Stopwords.java
 *    Copyright (C) 2001 Eibe Frank
 */

//package weka.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

/**
 * Class that can test whether a given string is a stop word.
 * Lowercases all words before the test. <p/>
 * The format for reading and writing is one word per line, lines starting
 * with '#' are interpreted as comments and therefore skipped. <p/>
 * The default stopwords are based on <a href="http://www.cs.cmu.edu/~mccallum/bow/rainbow/" target="_blank">Rainbow</a>. <p/>
 *
 * Accepts the following parameter: <p/>
 *
 * -i file <br/>
 * loads the stopwords from the given file <p/>
 *
 * -o file <br/>
 * saves the stopwords to the given file <p/>
 *
 * -p <br/>
 * outputs the current stopwords on stdout <p/>
 *
 * Any additional parameters are interpreted as words to test as stopwords.
 * 
 * @author Eibe Frank (eibe@cs.waikato.ac.nz)
 * @author Ashraf M. Kibriya (amk14@cs.waikato.ac.nz)
 * @author FracPete (fracpete at waikato dot ac dot nz)
 * @version $Revision: 1.4 $
 */
public class Stopwords {
  
  /** The hash set containing the list of stopwords */
  protected HashSet m_Words = null;

  /** The default stopwords object (stoplist based on Rainbow) */
  protected static Stopwords m_Stopwords;

  static {
    if (m_Stopwords == null) {
      m_Stopwords = new Stopwords();
    }
  }

  /**
   * initializes the stopwords (based on <a href="http://www.cs.cmu.edu/~mccallum/bow/rainbow/" target="_blank">Rainbow</a>).
   */
  public Stopwords() {
    m_Words = new HashSet();

    //Stopwords list from Rainbow
    add("a");
    add("able");
    add("about");
    add("above");
    add("according");
    add("accordingly");
    add("across");
    add("actually");
    add("after");
    add("afterwards");
    add("again");
    add("against");
    add("all");
    add("allow");
    add("allows");
    add("almost");
    add("alone");
    add("along");
    add("already");
    add("also");
    add("although");
    add("always");
    add("am");
    add("among");
    add("amongst");
    add("an");
    add("and");
    add("another");
    add("any");
    add("anybody");
    add("anyhow");
    add("anyone");
    add("anything");
    add("anyway");
    add("anyways");
    add("anywhere");
    add("apart");
    add("appear");
    add("appreciate");
    add("appropriate");
    add("are");
    add("around");
    add("as");
    add("aside");
    add("ask");
    add("asking");
    add("associated");
    add("at");
    add("available");
    add("away");
    add("awfully");
    add("b");
    add("be");
    add("became");
    add("because");
    add("become");
    add("becomes");
    add("becoming");
    add("been");
    add("before");
    add("beforehand");
    add("behind");
    add("being");
    add("believe");
    add("below");
    add("beside");
    add("besides");
    add("best");
    add("better");
    add("between");
    add("beyond");
    add("both");
    add("brief");
    add("but");
    add("by");
    add("c");
    add("came");
    add("can");
    add("cannot");
    add("cant");
    add("cause");
    add("causes");
    add("certain");
    add("certainly");
    add("changes");
    add("clearly");
    add("co");
    add("com");
    add("come");
    add("comes");
    add("concerning");
    add("consequently");
    add("consider");
    add("considering");
    add("contain");
    add("containing");
    add("contains");
    add("corresponding");
    add("could");
    add("course");
    add("currently");
    add("d");
    add("definitely");
    add("described");
    add("despite");
    add("did");
    add("different");
    add("do");
    add("does");
    add("doing");
    add("done");
    add("down");
    add("downwards");
    add("during");
    add("e");
    add("each");
    add("edu");
    add("eg");
    add("eight");
    add("either");
    add("else");
    add("elsewhere");
    add("enough");
    add("entirely");
    add("especially");
    add("et");
    add("etc");
    add("even");
    add("ever");
    add("every");
    add("everybody");
    add("everyone");
    add("everything");
    add("everywhere");
    add("ex");
    add("exactly");
    add("example");
    add("except");
    add("f");
    add("far");
    add("few");
    add("fifth");
    add("first");
    add("five");
    add("followed");
    add("following");
    add("follows");
    add("for");
    add("former");
    add("formerly");
    add("forth");
    add("four");
    add("from");
    add("further");
    add("furthermore");
    add("g");
    add("get");
    add("gets");
    add("getting");
    add("given");
    add("gives");
    add("go");
    add("goes");
    add("going");
    add("gone");
    add("got");
    add("gotten");
    add("greetings");
    add("h");
    add("had");
    add("happens");
    add("hardly");
    add("has");
    add("have");
    add("having");
    add("he");
    add("hello");
    add("help");
    add("hence");
    add("her");
    add("here");
    add("hereafter");
    add("hereby");
    add("herein");
    add("hereupon");
    add("hers");
    add("herself");
    add("hi");
    add("him");
    add("himself");
    add("his");
    add("hither");
    add("hopefully");
    add("how");
    add("howbeit");
    add("however");
    add("i");
    add("ie");
    add("if");
    add("ignored");
    add("immediate");
    add("in");
    add("inasmuch");
    add("inc");
    add("indeed");
    add("indicate");
    add("indicated");
    add("indicates");
    add("inner");
    add("insofar");
    add("instead");
    add("into");
    add("inward");
    add("is");
    add("it");
    add("its");
    add("itself");
    add("j");
    add("just");
    add("k");
    add("keep");
    add("keeps");
    add("kept");
    add("know");
    add("knows");
    add("known");
    add("l");
    add("last");
    add("lately");
    add("later");
    add("latter");
    add("latterly");
    add("least");
    add("less");
    add("lest");
    add("let");
    add("like");
    add("liked");
    add("likely");
    add("little");
    add("ll"); //added to avoid words like you'll,I'll etc.
    add("look");
    add("looking");
    add("looks");
    add("ltd");
    add("m");
    add("mainly");
    add("many");
    add("may");
    add("maybe");
    add("me");
    add("mean");
    add("meanwhile");
    add("merely");
    add("might");
    add("more");
    add("moreover");
    add("most");
    add("mostly");
    add("much");
    add("must");
    add("my");
    add("myself");
    add("n");
    add("name");
    add("namely");
    add("nd");
    add("near");
    add("nearly");
    add("necessary");
    add("need");
    add("needs");
    add("neither");
    add("never");
    add("nevertheless");
    add("new");
    add("next");
    add("nine");
    add("no");
    add("nobody");
    add("non");
    add("none");
    add("noone");
    add("nor");
    add("normally");
    add("not");
    add("nothing");
    add("novel");
    add("now");
    add("nowhere");
    add("o");
    add("obviously");
    add("of");
    add("off");
    add("often");
    add("oh");
    add("ok");
    add("okay");
    add("old");
    add("on");
    add("once");
    add("one");
    add("ones");
    add("only");
    add("onto");
    add("or");
    add("other");
    add("others");
    add("otherwise");
    add("ought");
    add("our");
    add("ours");
    add("ourselves");
    add("out");
    add("outside");
    add("over");
    add("overall");
    add("own");
    add("p");
    add("particular");
    add("particularly");
    add("per");
    add("perhaps");
    add("placed");
    add("please");
    add("plus");
    add("possible");
    add("presumably");
    add("probably");
    add("provides");
    add("q");
    add("que");
    add("quite");
    add("qv");
    add("r");
    add("rather");
    add("rd");
    add("re");
    add("really");
    add("reasonably");
    add("regarding");
    add("regardless");
    add("regards");
    add("relatively");
    add("respectively");
    add("right");
    add("s");
    add("said");
    add("same");
    add("saw");
    add("say");
    add("saying");
    add("says");
    add("second");
    add("secondly");
    add("see");
    add("seeing");
    add("seem");
    add("seemed");
    add("seeming");
    add("seems");
    add("seen");
    add("self");
    add("selves");
    add("sensible");
    add("sent");
    add("serious");
    add("seriously");
    add("seven");
    add("several");
    add("shall");
    add("she");
    add("should");
    add("since");
    add("six");
    add("so");
    add("some");
    add("somebody");
    add("somehow");
    add("someone");
    add("something");
    add("sometime");
    add("sometimes");
    add("somewhat");
    add("somewhere");
    add("soon");
    add("sorry");
    add("specified");
    add("specify");
    add("specifying");
    add("still");
    add("sub");
    add("such");
    add("sup");
    add("sure");
    add("t");
    add("take");
    add("taken");
    add("tell");
    add("tends");
    add("th");
    add("than");
    add("thank");
    add("thanks");
    add("thanx");
    add("that");
    add("thats");
    add("the");
    add("their");
    add("theirs");
    add("them");
    add("themselves");
    add("then");
    add("thence");
    add("there");
    add("thereafter");
    add("thereby");
    add("therefore");
    add("therein");
    add("theres");
    add("thereupon");
    add("these");
    add("they");
    add("think");
    add("third");
    add("this");
    add("thorough");
    add("thoroughly");
    add("those");
    add("though");
    add("three");
    add("through");
    add("throughout");
    add("thru");
    add("thus");
    add("to");
    add("together");
    add("too");
    add("took");
    add("toward");
    add("towards");
    add("tried");
    add("tries");
    add("truly");
    add("try");
    add("trying");
    add("twice");
    add("two");
    add("u");
    add("un");
    add("under");
    add("unfortunately");
    add("unless");
    add("unlikely");
    add("until");
    add("unto");
    add("up");
    add("upon");
    add("us");
    add("use");
    add("used");
    add("useful");
    add("uses");
    add("using");
    add("usually");
    add("uucp");
    add("v");
    add("value");
    add("various");
    add("ve"); //added to avoid words like I've,you've etc.
    add("very");
    add("via");
    add("viz");
    add("vs");
    add("w");
    add("want");
    add("wants");
    add("was");
    add("way");
    add("we");
    add("welcome");
    add("well");
    add("went");
    add("were");
    add("what");
    add("whatever");
    add("when");
    add("whence");
    add("whenever");
    add("where");
    add("whereafter");
    add("whereas");
    add("whereby");
    add("wherein");
    add("whereupon");
    add("wherever");
    add("whether");
    add("which");
    add("while");
    add("whither");
    add("who");
    add("whoever");
    add("whole");
    add("whom");
    add("whose");
    add("why");
    add("will");
    add("willing");
    add("wish");
    add("with");
    add("within");
    add("without");
    add("wonder");
    add("would");
    add("would");
    add("x");
    add("y");
    add("yes");
    add("yet");
    add("you");
    add("your");
    add("yours");
    add("yourself");
    add("yourselves");
    add("z");
    add("zero");
    // add new
    add("i'm");
    add("he's");
    add("she's");
    add("you're");
    add("i'll");
    add("you'll");
    add("she'll");
    add("he'll");
    add("it's");
    add("don't");
    add("can't");
    add("didn't");
    add("i've");
    add("that's");
    add("there's");
    add("isn't");
    add("what's");
    add("rt");
    add("doesn't");
    add("w/");
    add("w/o");
    add("太多");
    add("式");
    add("没");
    add("没有");
    add("多");
    add("什");
    add("来");
    add("大");
    add("种");
    add("了");
    add("学");
    add("期");
    add("排");
    add("觉得");
    add("很多");
    add("下来");
    add("该");
    add("一会儿");
    add("说实话");
    add("君");
    add("写");
    add("需要");
    add("依然");
    add("几十");
    add("十几");
    add("看");
    add("请问");
    add("每次");
    add("刚刚");
    add("不");
    add("好不好");
    add("些");
    add("才行");
    add("这门课");
    add("有所");
    add("名");
    add("目前");
    add("完全");
    add("只不过");
    add("何以");
    add("每次");
    add("实际上");
    add("主要");
    add("也许");
    add("星期");
    add("最近");
    add("可以");
    add("相");
    add("放");
    add("整个");
    add("这家");
    add("找");
    add("未免");
    add("特别");
    add("始终");
    add("相当");
    add("啊啊");
    add("多么");
    add("总是");
    add("顺便");
    add("无比");
    add("可想而知");
    add("从事");
    add("见到");
    add("无论如何");
    add("显然");
    add("最终");
    add("处");
    add("涉及");
    add("处在");
    add("带来");
    add("处于");
    add("留给");
    add("不禁");
    add("具有");
    add("回来");
    add("包括");
    add("每个");
    add("下下");
    add("所有");
    add("发现");
    add("确实");
    add("貌似");
    add("说不定");
    add("尤其");
    add("估计");
    add("专门");
    add("致");
    add("原来");
    add("总");
    add("方面");
    add("做到");
    add("觉着");
    add("每天");
    add("每周");
    add("每月");
    add("越");
    add("越来越");
    add("区");
    add("求");
    add("继续");
    add("似乎");
    add("年");
    add("门");
    add("在于");
    add("当年");
    add("知道");
    add("在这 ");
    add("当时");
    add("一枚");
    add("一座");
    add("来了");
    add("那时候");
    add("而我");
    add("心里");
    add("去年");
    add("常在");
    add("一条");
    add("半个");
    add("两人");
    add("一群");
    add("之人");
    add("很多人");
    add("可能会");
    add("段");
    add("贴了");
    add("那么多");
    add("一些人");
    add("下了");
    add("那一刻");
    add("过往");
    add("一颗");
    add("境");
    add("一晚");
    add("一组");
    add("今年");
    add("下面");
    add("要知道");
    add("后面");
    add("小时");
    add("路上");
    add("昨晚");
    add("意思");
    add("身边");
    add("感觉");
    add("顾着");
    add("身上");
    add("前面");
    add("这座");
    add("给我");
    add("一张");
    add("中间");
    add("一直在");
    add("告诉我");
    add("对我说");
    add("好了");
    add("大半");
    add("出于");
    add("他俩");
    add("就有");
    add("成了");
    add("又是");
    add("又要");
    add("才是");
    add("一看");
    add("一身");
    add("受");
    add("给人");
    add("会在");
    add("算了");
    add("能去");
    add("道");
    add("夏");
    add("一名");
    add("一只");
    add("儿");
    add("下午");
    add("中午");
    add("早上");
    add("晚上");
    add("为由");
    add("第二天");
    add("一股");
    add("干");
    add("发");
    add("子");
    add("了吧");
    add("三个");
    add("三头");
    add("两头");
    add("能不能");
    add("说的");
    add("一句");
    add("十分");
    add("成为");
    add("又不是");
    add("看了");
    add("那一");
    add("年时");
    add("两分");
    add("都有");
    add("是否");
    add("有人");
    add("以前");
    add("但我");
    add("一眼");
    add("一天");
    add("也有");
    add("天天");
    add("本来");
    add("真是");
    add("算是");
    add("要来");
    add("还要");
    add("要去");
    add("下一");
    add("进");
    add("之前");
    add("走了");
    add("上了");
    add("真的");
    add("已");
    add("今天");
    add("昨天");
    add("明天");
    add("后天");
    add("想了");
    add("两个");
    add("是什么");
    add("都在");
    add("正在");
    add("一脸");
    add("觉得");
    add("过去");
    add("突然");
    add("是因为");
    add("只");
    add("喝");
    add("走");
    add("一把");
    add("应该");
    add("应该是");
    add("回");
    add("全");
    add("艺");
    add("是不是");
    add("还");
    add("就像");
    add("事");
    add("上");
    add("晶");
    add("对");
    add("去了");
    add("高");
    add("肯定");
    add("贴");
    add("成");
    add("东");
    add("科");
    add("俩");
    add("西");
    add("再");
    add("北");
    add("迁");
    add("说是");
    add("代");
    add("点");
    add("一次");
    add("一起");
    add("一直");
    add("问");
    add("人在");
    add("挺");
    add("问");
    add("祥");
    add("一边");
    add("里面");
    add("这是");
    add("几个");
    add("先");
    add("前");
    add("这个人");
    add("进了");
    add("了吗");
    add("看到");
    add("来了");
    add("看着");
    add("已经");
    add("正和");
    add("请");
    add("年来");
    add("这一");
    add("下");
    add("样");
    add("花");
    add("一想");
    add("之后");
    add("可");
    add("佘");
    add("一个");
    add("的人");
    add("这种");
    add("能够");
    add("都是");
    add("现在");
    add("以后");
    add("非");
    add("想");
    add("中");
    add("太");
    add("一下");
    add("一种");
    add("一点");
    add("时");
    add("最");
    add("也是");
    add("说");
    add("说了");
    add("里");
    add("却");
    add("一场");
    add("后");
    add("以为");
    add("一位");
    add("最后");
    add("候");
    add("小");
    add("系");
    add("做");
    add("一些");
    add("做的");
    add("有点");
    add("到了");
    add("人");
    add("人民");
    add("啊");
    add("阿");
    add("哎");
    add("哎呀");
    add("哎哟");
    add("唉");
    add("俺");
    add("俺们");
    add("按");
    add("按照");
    add("吧");
    add("吧哒");
    add("把");
    add("罢了");
    add("被");
    add("本");
    add("本着");
    add("比");
    add("比方");
    add("比如");
    add("鄙人");
    add("彼");
    add("彼此");
    add("边");
    add("别");
    add("别的");
    add("别说");
    add("并");
    add("并且");
    add("不比");
    add("不成");
    add("不单");
    add("不但");
    add("不独");
    add("不管");
    add("不光");
    add("不过");
    add("不仅");
    add("不拘");
    add("不论");
    add("不怕");
    add("不然");
    add("不如");
    add("不特");
    add("不惟");
    add("不问");
    add("不只");
    add("朝");
    add("朝着");
    add("趁");
    add("趁着");
    add("乘");
    add("冲");
    add("除");
    add("除此之外");
    add("除非");
    add("除了");
    add("此");
    add("此间");
    add("此外");
    add("从");
    add("从而");
    add("打");
    add("待");
    add("但");
    add("但是");
    add("当");
    add("当着");
    add("当时");
    add("到");
    add("得");
    add("的");
    add("的话");
    add("等");
    add("等等");
    add("地");
    add("第");
    add("叮咚");
    add("对");
    add("对于");
    add("多少");
    add("而");
    add("而况");
    add("而是");
    add("而外");
    add("而言");
    add("而已");
    add("尔后");
    add("反过来");
    add("反过来说");
    add("反之");
    add("非但");
    add("非徒");
    add("否则");
    add("嘎");
    add("嘎登");
    add("该");
    add("赶");
    add("个");
    add("各");
    add("各个");
    add("各位");
    add("各种");
    add("各自");
    add("给");
    add("根据");
    add("跟");
    add("故");
    add("故此");
    add("固然");
    add("关于");
    add("管");
    add("归");
    add("果然");
    add("果真");
    add("过");
    add("哈");
    add("哈哈");
    add("呵");
    add("和");
    add("何");
    add("何处");
    add("何况");
    add("何时");
    add("嘿");
    add("哼");
    add("哼唷");
    add("呼哧");
    add("乎");
    add("哗");
    add("还是");
    add("还有");
    add("换句话说");
    add("换言之");
    add("或");
    add("或是");
    add("或者");
    add("极了");
    add("及");
    add("及其");
    add("及至");
    add("即");
    add("即便");
    add("即或");
    add("即令");
    add("即若");
    add("即使");
    add("几");
    add("几时");
    add("己");
    add("既");
    add("既然");
    add("既是");
    add("继而");
    add("加之");
    add("假如");
    add("假若");
    add("假使");
    add("鉴于");
    add("将");
    add("较");
    add("较之");
    add("叫");
    add("接着");
    add("结果");
    add("借");
    add("紧接着");
    add("进而");
    add("尽");
    add("尽管");
    add("经");
    add("经过");
    add("就");
    add("就是");
    add("就是说");
    add("据");
    add("具体地说");
    add("具体说来");
    add("开始");
    add("开外");
    add("靠");
    add("咳");
    add("可");
    add("可见");
    add("可是");
    add("况且");
    add("啦");
    add("来");
    add("来着");
    add("离");
    add("例如");
    add("哩");
    add("连");
    add("连同");
    add("两者");
    add("了");
    add("临");
    add("另");
    add("另外");
    add("另一方面");
    add("论");
    add("嘛");
    add("吗");
    add("慢说");
    add("漫说");
    add("冒");
    add("么");
    add("每");
    add("每当");
    add("们");
    add("莫若");
    add("某");
    add("某个");
    add("某些");
    add("拿");
    add("哪");
    add("哪边");
    add("哪儿");
    add("哪个");
    add("哪里");
    add("哪年");
    add("哪怕");
    add("哪天");
    add("哪些");
    add("哪样");
    add("那");
    add("那边");
    add("那儿");
    add("那个");
    add("那会儿");
    add("那里");
    add("那么");
    add("那么些");
    add("那么样");
    add("那时");
    add("那些");
    add("那样");
    add("乃");
    add("乃至");
    add("呢");
    add("能");
    add("你");
    add("你们");
    add("您");
    add("宁");
    add("宁可");
    add("宁肯");
    add("宁愿");
    add("哦");
    add("呕");
    add("啪达");
    add("旁人");
    add("呸");
    add("凭");
    add("凭借");
    add("其");
    add("其次");
    add("其二");
    add("其他");
    add("其它");
    add("其一");
    add("其余");
    add("其中");
    add("起");
    add("起见");
    add("岂但");
    add("恰恰相反");
    add("前后");
    add("前者");
    add("且");
    add("然而");
    add("然后");
    add("然则");
    add("让");
    add("人家");
    add("任");
    add("任何");
    add("任凭");
    add("如");
    add("如此");
    add("如果");
    add("如何");
    add("如其");
    add("如若");
    add("如上所述");
    add("若");
    add("若非");
    add("若是");
    add("啥");
    add("上下");
    add("尚且");
    add("设若");
    add("设使");
    add("甚而");
    add("甚么");
    add("甚至");
    add("省得");
    add("时候");
    add("有时候");
    add("什么");
    add("什么样");
    add("使得");
    add("是");
    add("是的");
    add("首先");
    add("谁");
    add("谁知");
    add("顺");
    add("来得");
    add("顺着");
    add("似的");
    add("虽");
    add("虽然");
    add("虽说");
    add("虽则");
    add("随");
    add("随着");
    add("所");
    add("所以");
    add("他");
    add("他们");
    add("他人");
    add("他说");
    add("它");
    add("它们");
    add("她");
    add("她们");
    add("她说");
    add("倘");
    add("倘或");
    add("倘然");
    add("倘若");
    add("倘使");
    add("腾");
    add("替");
    add("通过");
    add("同");
    add("同时");
    add("哇");
    add("万一");
    add("往");
    add("望");
    add("为");
    add("为何");
    add("为了");
    add("为什么");
    add("为着");
    add("喂");
    add("嗡嗡");
    add("我");
    add("我们");
    add("呜");
    add("呜呼");
    add("乌乎");
    add("无论");
    add("无宁");
    add("毋宁");
    add("嘻");
    add("吓");
    add("相对而言");
    add("相比之下");
    add("像");
    add("向");
    add("向着");
    add("嘘");
    add("呀");
    add("焉");
    add("沿");
    add("沿着");
    add("要");
    add("要不");
    add("要不然");
    add("要不是");
    add("要么");
    add("要是");
    add("也");
    add("也罢");
    add("也好");
    add("一");
    add("一旦");
    add("一方面");
    add("一来");
    add("一切");
    add("一波");
    add("一则");
    add("一定");
    add("依");
    add("依照");
    add("矣");
    add("以");
    add("以便");
    add("以及");
    add("以免");
    add("以至");
    add("以至于");
    add("以致");
    add("抑或");
    add("因");
    add("因此");
    add("因而");
    add("因为");
    add("哟");
    add("用");
    add("由");
    add("由此可见");
    add("由于");
    add("有");
    add("有的");
    add("有关");
    add("有些");
    add("又");
    add("于");
    add("于是");
    add("于是乎");
    add("与");
    add("与此同时");
    add("与否");
    add("与其");
    add("越是");
    add("云云");
    add("哉");
    add("再说");
    add("再者");
    add("在");
    add("在下");
    add("咱");
    add("咱们");
    add("则");
    add("怎");
    add("怎么");
    add("怎么办");
    add("怎么样");
    add("怎样");
    add("咋");
    add("照");
    add("照着");
    add("者");
    add("这");
    add("这边");
    add("这儿");
    add("这个");
    add("这条");
    add("这会儿");
    add("这就是说");
    add("这里");
    add("这么");
    add("这么点儿");
    add("这么些");
    add("这么样");
    add("这时");
    add("这些");
    add("这样");
    add("正如");
    add("吱");
    add("之");
    add("之类");
    add("之所以");
    add("之一");
    add("只是");
    add("只限");
    add("只要");
    add("只有");
    add("至");
    add("至于");
    add("诸位");
    add("着");
    add("着呢");
    add("自");
    add("自从");
    add("自个儿");
    add("自各儿");
    add("自己");
    add("自家");
    add("自身");
    add("综上所述");
    add("总的来看");
    add("总的来说");
    add("总的说来");
    add("总而言之");
    add("总之");
    add("纵");
    add("纵令");
    add("纵然");
    add("纵使");
    add("遵照");
    add("作为");
    add("兮");
    add("呃");
    add("呗");
    add("咚");
    add("咦");
    add("喏");
    add("啐");
    add("喔唷");
    add("嗬");
    add("嗯");
    add("嗳");
    add("~");
    add("!");
    add("！");
    add("，");
    add("。");
    add(".");
    add(":");
    add("会");
    add("觉");
    add("号");
    add("很");
    add("楼");
    add("都");
    add("使");
    add("部");
  }

  /**
   * removes all stopwords
   */
  public void clear() {
    m_Words.clear();
  }

  /**
   * adds the given word to the stopword list (is automatically converted to
   * lower case and trimmed)
   *
   * @param word the word to add
   */
  public void add(String word) {
    if (word.trim().length() > 0)
      m_Words.add(word.trim().toLowerCase());
  }

  /**
   * removes the word from the stopword list
   *
   * @param word the word to remove
   * @return true if the word was found in the list and then removed
   */
  public boolean remove(String word) {
    return m_Words.remove(word);
  }
  
  /**
   * Returns a sorted enumeration over all stored stopwords
   *
   * @return the enumeration over all stopwords
   */
  public Enumeration elements() {
    Iterator    iter;
    Vector      list;

    iter = m_Words.iterator();
    list = new Vector();

    while (iter.hasNext())
      list.add(iter.next());

    // sort list
    Collections.sort(list);

    return list.elements();
  }

  /**
   * Generates a new Stopwords object from the given file
   *
   * @param filename the file to read the stopwords from
   * @throws Exception if reading fails
   */
  public void read(String filename) throws Exception {
    read(new File(filename));
  }

  /**
   * Generates a new Stopwords object from the given file
   *
   * @param file the file to read the stopwords from
   * @throws Exception if reading fails
   */
  public void read(File file) throws Exception {
    read(new BufferedReader(new FileReader(file)));
  }

  /**
   * Generates a new Stopwords object from the reader. The reader is
   * closed automatically.
   *
   * @param reader the reader to get the stopwords from
   * @throws Exception if reading fails
   */
  public void read(BufferedReader reader) throws Exception {
    String      line;

    clear();
    
    while ((line = reader.readLine()) != null) {
      line = line.trim();
      // comment?
      if (line.startsWith("#"))
        continue;
      add(line);
    }

    reader.close();
  }

  /**
   * Writes the current stopwords to the given file
   *
   * @param filename the file to write the stopwords to
   * @throws Exception if writing fails
   */
  public void write(String filename) throws Exception {
    write(new File(filename));
  }

  /**
   * Writes the current stopwords to the given file
   *
   * @param file the file to write the stopwords to
   * @throws Exception if writing fails
   */
  public void write(File file) throws Exception {
    write(new BufferedWriter(new FileWriter(file)));
  }

  /**
   * Writes the current stopwords to the given writer. The writer is closed
   * automatically.
   *
   * @param writer the writer to get the stopwords from
   * @throws Exception if writing fails
   */
  public void write(BufferedWriter writer) throws Exception {
    Enumeration   enm;

    // header
    writer.write("# generated " + new Date());
    writer.newLine();

    enm = elements();

    while (enm.hasMoreElements()) {
      writer.write(enm.nextElement().toString());
      writer.newLine();
    }

    writer.flush();
    writer.close();
  }

  /**
   * returns the current stopwords in a string
   *
   * @return the current stopwords
   */
  public String toString() {
    Enumeration   enm;
    StringBuffer  result;

    result = new StringBuffer();
    enm    = elements();
    while (enm.hasMoreElements()) {
      result.append(enm.nextElement().toString());
      if (enm.hasMoreElements())
        result.append(",");
    }

    return result.toString();
  }
  
  /** 
   * Returns true if the given string is a stop word.
   * 
   * @param word the word to test
   * @return true if the word is a stopword
   */
  public boolean is(String word) {
    return m_Words.contains(word.toLowerCase());
  }

/** 
   * Returns true if the given string is a stop word.
   * 
   * @param str the word to test
   * @return true if the word is a stopword
   */
  public static boolean isStopword(String str) {
    return m_Stopwords.is(str.toLowerCase());
  }

}
