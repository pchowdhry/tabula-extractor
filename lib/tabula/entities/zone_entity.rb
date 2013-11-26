module Tabula

  class ZoneEntity < java.awt.geom.Rectangle2D::Float

    attr_accessor :texts

    def initialize(top, left, width, height)
      super()
      if left && top && width && height
        self.java_send :setRect, [Java::float, Java::float, Java::float, Java::float,], left, top, width, height
      end
      self.texts = []
    end

    def merge!(other)
      self.top    = [self.top, other.top].min
      self.left   = [self.left, other.left].min
      self.width  = [self.right, other.right].max - left
      self.height = [self.bottom, other.bottom].max - top

      self.java_send :setRect, [Java::float, Java::float, Java::float, Java::float,], self.left, self.top, self.width, self.height
    end

    ##
    # default sorting order for ZoneEntity objects
    # is lexicographical (left to right, top to bottom)
    def <=>(other)
      return  1 if self.left > other.left
      return -1 if self.left < other.left
      return  1 if self.top  > other.top
      return -1 if self.top  < other.top
      return  0
    end

    def to_json(options={})
      self.to_h.to_json
    end

    def tlbr
      [top, left, bottom, right]
    end
  end
end