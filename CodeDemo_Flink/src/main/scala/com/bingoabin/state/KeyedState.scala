package com.bingoabin.state

//#Keyed State：
//#顾名思义就是基于KeyedStream上的状态，这个状态是跟特定的Key 绑定的。KeyedStream流上的每一个Key，都对应一个State。Flink针对 Keyed State 提供了以下可以保存State的数据结构.
//
//#Keyed state托管状态有六种类型：
//#1、ValueState
//保存一个可以更新和检索的值（如上所述，每个值都对应到当前的输入数据的key，因此算子接收到的每个key都可能对应一个值）。 这个值可以通过update(T) 进行更新，通过 T value() 进行检索
//#2、ListState
//保存一个元素的列表。可以往这个列表中追加数据，并在当前的列表上进行检索。可以通过 add(T) 或者 addAll(List<T>) 进行添加元素，通过 Iterable<T> get() 获得整个列表。还可以通过 update(List<T>) 覆盖当前的列表	。
//#3、MapState
//维护了一个映射列表。 你可以添加键值对到状态中，也可以获得 反映当前所有映射的迭代器。使用 put(UK，UV) 或者 putAll(Map<UK，UV>) 添加映射。 使用 get(UK) 检索特定 key。 使用 entries()，keys() 和 values() 分别检索映射、 键和值的可迭代视图。
//#4、ReducingState
//保存一个单值，表示添加到状态的所有值的聚合。接口与ListState类似，但使用add(T) 增加元素，会使用提供的 ReduceFunction 进行聚合。
//#5、AggregatingState
//AggregatingState<IN, OUT>: 保留一个单值，表示添加到状态的所有值的聚合。和 ReducingState 相反的是, 聚合类型可能与添加到状态的元素的类型不同。 接口与 ListState类似，但使用 add(IN) 添加的元素会用指定的 AggregateFunction 进行聚合
//#6、FoldingState
//保留一个单值，表示添加到状态的所有值的聚合。 与ReducingState 相反，聚合类型可能与添加到状态的元素类型不同。接口与 ListState 类似，但使用 add（T）添加的元素会用指定的 FoldFunction 折叠成聚合值。在Flink1.4中弃用，未来版本将被完全删除。
object KeyedState {

}
