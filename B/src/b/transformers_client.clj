(ns b.transformers-client)

(def transforms (atom [#(.toUpperCase %)]))

(defn load-a-transform []
  (require 'a.transformers)
  (swap! transforms conj (ns-resolve *ns* 'a.transformers/transform-a)))

(defn apply-transforms [s] (reduce #(%2 %1) s @transforms))